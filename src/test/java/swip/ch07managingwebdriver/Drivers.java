package swip.ch07managingwebdriver;

import org.apache.commons.lang3.ClassUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.logging.Logger;

public class Drivers {

    private static final Logger LOGGER = Logger.getLogger(Drivers.class.getName());
    public static WebDriver driverWithAddedShutdownHook(WebDriver driver) {
        Runtime.getRuntime().addShutdownHook(new Thread(driver::quit)); // # add the shutdown hook to clear up
        return driver;
    }


    public static WebDriver baseUrlDriver(WebDriver driver) {

        Class<?> driverClass = driver.getClass();
        List<Class<?>> allInterfaces = ClassUtils.getAllInterfaces(driverClass);
        return (WebDriver) Proxy.newProxyInstance(
                driverClass.getClassLoader(), // # use the driver's class loader
                allInterfaces.toArray(new Class[allInterfaces.size()]), // use Apache Commons to find all the interfaces the class implements, including that its super-class
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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
                    }
                });

    }


    public static WebDriver cleaned(WebDriver driver) {
        try {
            Alert alert = ExpectedConditions.alertIsPresent().apply(driver);
            if (alert != null) {
                alert.dismiss(); // <1> If an alert is present we must close it.
            }
        } catch (UnsupportedOperationException | UnsupportedCommandException ignored) {
            // not all browsers support this
            LOGGER.info("failed to close alert " + driver + " unsupported operation");
        }
        driver.manage().deleteAllCookies(); // <2> Delete any cookies that have been set.
        return driver;
    }


}
