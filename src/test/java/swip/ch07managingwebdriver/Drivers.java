package swip.ch07managingwebdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.logging.Logger;

public class Drivers {

    private static final Logger LOGGER = Logger.getLogger(Drivers.class.getName());
    public static WebDriver driverWithAddedShutdownHook(WebDriver driver) {
        Runtime.getRuntime().addShutdownHook(new Thread(driver::quit)); // # add the shutdown hook to clear up
        return driver;
    }

    public static WebDriver cleaned(WebDriver driver) {
        try {
            Alert alert = ExpectedConditions.alertIsPresent().apply(driver);
            if (alert != null) {
                alert.dismiss(); // <1> If an alert is present we must close it.
            }
        } catch (UnsupportedOperationException | UnsupportedCommandException ignored) {
            // not all browsers support this
            LOGGER.info("failed to close alert " + driver + " unsupported operation/command");
        }
        driver.manage().deleteAllCookies(); // <2> Delete any cookies that have been set.

        if (driver.getWindowHandles().size() > 1) {

            driver.switchTo().defaultContent();

            String topWindowHandle = driver.getWindowHandle();

            for (String windowHandle : driver.getWindowHandles()) {
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
