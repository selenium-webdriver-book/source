package swip.ch11decorating.baseurl;

import org.apache.commons.lang3.ClassUtils;
import org.openqa.selenium.WebDriver;
import swip.ch07managingwebdriver.ConfigFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.List;

public class BaseUrlDecorator {

    public static WebDriver baseUrlDriver(WebDriver driver) {

        Class<?> driverClass = driver.getClass();
        List<Class<?>> allInterfaces = ClassUtils.getAllInterfaces(driverClass);
        return (WebDriver) Proxy.newProxyInstance(
                driverClass.getClassLoader(), // # use the driver's class loader
                allInterfaces.toArray(new Class[allInterfaces.size()]), // use Apache Commons to find all the interfaces the class implements, including that its super-class
                (proxy, method, args) -> {
                    if (method.getName().equals("get")) { // # if the method we call is the get method
                        String url = args[0].toString();
                        if (!url.contains("://")) { // # and the method's parameter is not an absolute URL
                            args[0] = ConfigFactory.BASE_URL + url; // # then make it relative to the base URL
                        }
                    }

                    try {
                        return method.invoke(driver, args); // # invoke the method, maybe using our updated URL
                    } catch (InvocationTargetException e) {
                        throw e.getCause(); // # if there is an exception, un-wrap it
                    }
                });

    }

}
