package swip.ch12decorating.httpstatuscode;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;
import net.lightbody.bmp.proxy.http.BrowserMobHttpResponse;
import org.apache.commons.lang3.ClassUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.util.List;

/**
 * @see HasHttpStatusCode
 */
public class HttpStatusCodeDecorator {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpStatusCodeDecorator.class);

    private static Class[] getInterfaces(Class<?> driverClass) {
        List<Class<?>> allInterfaces = ClassUtils.getAllInterfaces(driverClass);
        allInterfaces.add(HasHttpStatusCode.class);
        return allInterfaces.toArray(new Class[allInterfaces.size()]);
    }

    private static InvocationHandler getInvocationHandler(WebDriver driver,
                                                          ProxyServer server, URI baseUrl) {
        return new InvocationHandler() {
            private int httpStatusCode;

            {
                server.addResponseInterceptor(
                        (BrowserMobHttpResponse httpResponse, Har har) -> {
                            String path = httpResponse.getEntry().getRequest().getUrl();
                            boolean capture = !path.matches(".*\\.(js|css|ico|json)") &&
                                    path.startsWith(baseUrl.toString());
                            LOGGER.info("path={}, capture={}", path, capture);
                            if (httpResponse.getRawResponse() != null && capture) {
                                httpStatusCode = httpResponse.getRawResponse()
                                        .getStatusLine().getStatusCode();
                                LOGGER.info("httpStatusCode={}", httpStatusCode);
                            }
                        });
            }

            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                switch (method.getName()) {
                    case "getHttpStatusCode":
                        // a short wait, as interceptors occur on another thread, and I cannot see a straight-forward
                        // way of getting this to work
                        Thread.sleep(1000);
                        LOGGER.info("requesting status code, httpStatusCode={}", httpStatusCode);
                        if (httpStatusCode == 0) {
                            throw new IllegalStateException(
                                    "no request has yet been successfully intercepted");
                        }
                        return httpStatusCode;
                }

                try {
                    return method.invoke(driver, args);
                } catch (InvocationTargetException e) {
                    throw e.getCause();
                }
            }
        };
    }

    public static WebDriver httpStatusCodeDriver(WebDriver driver,
                                                 ProxyServer server,
                                                 URI baseUrl) {

        Class<?> driverClass = driver.getClass();

        Class[] interfaces = getInterfaces(driverClass);

        InvocationHandler invocationHandler = getInvocationHandler(driver, server, baseUrl);

        return (WebDriver) Proxy.newProxyInstance(
                driverClass.getClassLoader(),
                interfaces,
                invocationHandler);
    }
}
