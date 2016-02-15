package swip.ch12wrapping.baseurl;

import org.apache.commons.lang3.ClassUtils;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.util.List;

public class BaseUrlDecorator {

    private static Class[] getInterfaces(Class<?> driverClass) {
        List<Class<?>> allInterfaces = ClassUtils.getAllInterfaces(driverClass);
        return allInterfaces.toArray(new Class[allInterfaces.size()]);
    }

    private static InvocationHandler getInvocationHandler(WebDriver driver, URI baseUrl) {
        return (proxy, method, args) -> {
            if (method.getName().equals("get")) {
                String url = args[0].toString();
                if (!url.contains("://")) {
                    args[0] = baseUrl + url;
                }
            }

            try {
                return method.invoke(driver, args);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        };
    }

    public static WebDriver baseUrlDriver(WebDriver driver, URI baseUrl) {

        Class<?> driverClass = driver.getClass();

        Class[] interfaces = getInterfaces(driverClass);

        InvocationHandler invocationHandler = getInvocationHandler(driver, baseUrl);

        return (WebDriver) Proxy.newProxyInstance(
                driverClass.getClassLoader(),
                interfaces,
                invocationHandler);
    }
}
