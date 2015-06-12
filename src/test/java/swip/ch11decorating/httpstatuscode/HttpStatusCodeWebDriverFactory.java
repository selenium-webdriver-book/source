package swip.ch11decorating.httpstatuscode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.lang.reflect.Proxy;
import java.util.function.Function;

public class HttpStatusCodeWebDriverFactory {
    private HttpStatusCodeWebDriverFactory() {
    }

    @SuppressWarnings("unchecked")
    public static <W extends WebDriver & HasHttpStatusCode> W create(
            Function<DesiredCapabilities, WebDriver> driverFactory) {
        return (W) Proxy.newProxyInstance( // create a new proxy
                HttpStatusCodeWebDriverFactory.class.getClassLoader(),
                new Class[]{WebDriver.class, HasHttpStatusCode.class}, // Implement both the WebDriver and HasHttpStatusCode interfaces.
                new HttpStatusCodeInvocationHandler(driverFactory));
    }
}
