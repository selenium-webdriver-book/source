package swip.ch08unicorns.windows;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.junit.Config;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;
import java.net.URI;

@RunWith(SeleniumWebDriverRunner.class)
@Config(exclude = {"browserName=safari"})
public class NewWindowIT {
    @Inject
    private WebDriver driver;
    @Inject
    private URI baseUrl;

    @Test
    public void openNewWindow() throws Exception {
        driver.get(baseUrl + "/open-a-new-window.html");

        String originalWindowHandle = driver.getWindowHandle(); // store the original window handle
        try { // wrap the new window operations in a try/finally block
            driver.findElement(By.tagName("a")).click(); // execute an operation to open a new window

            String newHandle = driver
                    .getWindowHandles()
                    .stream()
                    .filter(handle -> !handle.equals(originalWindowHandle)) // find a window that is not the current one
                    .findFirst()
                    .get();

            driver.switchTo().window(newHandle); // switch to the new window
            try {
                // ... perform operations on the new window
            } finally {
                driver.close(); // make sure the new window is close
            }

        } finally {
            driver.switchTo().window(originalWindowHandle); // switch back to the original window
        }
    }

    @Test
    public void openNewWindowUsingStrategy() throws Exception {
        driver.get(baseUrl + "/open-a-new-window.html");

        String originalWindowHandle = driver.getWindowHandle();
        try {
            driver.findElement(By.tagName("a")).click();

            driver
                    .getWindowHandles()
                    .stream()
                    .filter(handle -> driver
                            .switchTo()
                            .window(handle)
                            .findElement(By.tagName("h1"))
                            .getText()
                            .equals("Thank You!")) // find a window that has the text we want
                    .findFirst();

            try {
            } finally {
                driver.close();
            }

        } finally {
            driver.switchTo().window(originalWindowHandle);
        }
    }
}
