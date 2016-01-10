package swip.framework;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

class WebDriverCleaner {
    WebDriver cleanWebDriver(WebDriver driver) {

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();

        closeRedundantWindows(driver);

        driver.manage().window().setSize(new Dimension(768 / 2, 1024 / 2));

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
