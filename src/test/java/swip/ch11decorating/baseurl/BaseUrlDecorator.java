package swip.ch11decorating.baseurl;

import org.apache.commons.lang3.ClassUtils;
import org.openqa.selenium.WebDriver;
import swip.ch07managingwebdriver.ConfigFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

public class BaseUrlDecorator {

    private static Class[] getInterfaces(Class<?> driverClass) {
        List<Class<?>> allInterfaces = ClassUtils.getAllInterfaces(driverClass);
        return allInterfaces.toArray(new Class[allInterfaces.size()]);
    }

    private static InvocationHandler getInvocationHandler(WebDriver driver) {
        return (proxy, method, args) -> {
            if (method.getName().equals("get")) {
                String url = args[0].toString();
                if (!url.contains("://")) {
                    args[0] = ConfigFactory.BASE_URL + url;
                }
            }

            return method.invoke(driver, args);
        };
    }

    public static WebDriver baseUrlDriver(WebDriver driver) {

        Class<?> driverClass = driver.getClass();

        Class[] interfaces = getInterfaces(driverClass);

        InvocationHandler invocationHandler = getInvocationHandler(driver);

        return (WebDriver) Proxy.newProxyInstance(
                driverClass.getClassLoader(),
                interfaces,
                invocationHandler);
    }

}
