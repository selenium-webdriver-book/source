package swip.ch11decorating.httpstatuscode;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;
import net.lightbody.bmp.proxy.http.BrowserMobHttpResponse;
import org.apache.commons.lang3.ClassUtils;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class HttpStatusCodeDecorator {

    private static Class[] getInterfaces(Class<?> driverClass) {
        List<Class<?>> allInterfaces = ClassUtils.getAllInterfaces(driverClass);
        allInterfaces.add(HasHttpStatusCode.class);
        return allInterfaces.toArray(new Class[allInterfaces.size()]);
    }

    private static InvocationHandler getInvocationHandler(WebDriver driver, ProxyServer server) {
        return new InvocationHandler() {
            private int httpStatusCode;

            {
                server.addResponseInterceptor((BrowserMobHttpResponse httpResponse, Har har) -> {
                    if (httpResponse.getRawResponse() != null) {
                        httpStatusCode = httpResponse.getRawResponse().getStatusLine().getStatusCode();
                    }
                });

            }

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                switch (method.getName()) {
                    case "getHttpStatusCode":
                        if (httpStatusCode == 0) {
                            throw new IllegalStateException("no request has yet been successfully intercepted");
                        }
                        return httpStatusCode;
                }

                return method.invoke(driver, args);
            }
        };
    }

    public static WebDriver httpStatusCodeDriver(WebDriver driver, ProxyServer server) {

        Class<?> driverClass = driver.getClass();

        Class[] interfaces = getInterfaces(driverClass);

        InvocationHandler invocationHandler = getInvocationHandler(driver, server);

        return (WebDriver) Proxy.newProxyInstance(
                driverClass.getClassLoader(),
                interfaces,
                invocationHandler);
    }
}
