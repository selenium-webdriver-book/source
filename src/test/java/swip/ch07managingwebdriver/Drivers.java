package swip.ch07managingwebdriver;

import org.openqa.selenium.WebDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Drivers {

    private static final Logger LOGGER = Logger.getLogger(Drivers.class.getName());

    public static WebDriver driverWithAddedShutdownHook(WebDriver driver) {
        Runtime.getRuntime().addShutdownHook(new Thread(driver::quit)); // # add the shutdown hook to clear up
        return driver;
    }

    public static WebDriver cleaned(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();

        Set<String> windowHandles = driver.getWindowHandles();
        if (windowHandles.size() > 1) {

            driver.switchTo().defaultContent();

            String topWindowHandle = driver.getWindowHandle();

            for (String windowHandle : windowHandles) {
                if (!windowHandle.equals(topWindowHandle)) {
                    LOGGER.info("closing  surplus window " + windowHandle);
                    driver.switchTo().window(windowHandle);
                    driver.close();
                }
            }
        }

        return driver;
    }


}
