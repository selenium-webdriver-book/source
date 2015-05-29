package swip.ch08unicorns.httpstatuscode;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;
import net.lightbody.bmp.proxy.http.BrowserMobHttpResponse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

class HttpStatusCodeInvocationHandler implements InvocationHandler, AutoCloseable {
    private static final int PORT = 9090; // The port we will listen upon.
    private final ProxyServer server = new ProxyServer(PORT);
    private final WebDriver driver;
    private int httpStatusCode;

    HttpStatusCodeInvocationHandler(Function<DesiredCapabilities, WebDriver> driverFactory) {
        server.start();
        server.addResponseInterceptor((BrowserMobHttpResponse httpResponse, Har har) -> {
            if (httpResponse.getRawResponse() != null) {
                httpStatusCode = httpResponse.getRawResponse().getStatusLine().getStatusCode();
            }
        });

        DesiredCapabilities capabilities = new DesiredCapabilities(); // we need to have a factory here as we need to set the proxy
        capabilities.setCapability(CapabilityType.PROXY, new org.openqa.selenium.Proxy().setHttpProxy("localhost:" + PORT));
        driver = driverFactory.apply(capabilities);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        switch (method.getName()) {
            case "getHttpStatusCode": // as the driver will not have this method, we need to implement it here
                if (httpStatusCode == 0) { // we may not have gotten a code yet
                    throw new IllegalStateException("no request has yet been successfully intercepted");
                }
                return httpStatusCode;
            case "quit":
                close(); // we need to clean up the proxy as well as the driver
        }

        try {
            return method.invoke(driver, args);
        } catch (InvocationTargetException e) {
            throw e.getCause(); // un-wrap the exception to make it more readable
        }
    }

    @Override
    public void close() throws Exception {
        server.stop();
    }
}
