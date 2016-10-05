package swb.framework;

import org.openqa.selenium.WebDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

class WebDriverCleaner {
    WebDriver cleanWebDriver(WebDriver driver) {

        WebDriver.Options manage = driver.manage();

        manage.deleteAllCookies();

        WebDriver.Timeouts timeouts = manage.timeouts();

        timeouts.implicitlyWait(0, TimeUnit.SECONDS);
        timeouts.setScriptTimeout(0, TimeUnit.SECONDS);

        closeRedundantWindows(driver);

        return driver;
    }

    private void closeRedundantWindows(WebDriver driver) {
        Set<String> windowHandles = driver.getWindowHandles();
        if (windowHandles.size() > 1) {

            driver.switchTo().defaultContent();

            String topWindowHandle = driver.getWindowHandle();

            for (String windowHandle : windowHandles) {
                if (!windowHandle.equals(topWindowHandle)) {
                    driver.switchTo().window(windowHandle);
                    driver.close();
                }
            }
        }
    }
}
