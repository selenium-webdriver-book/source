package swip.ch08unicorns.windows;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch07managingwebdriver.Config;
import swip.ch07managingwebdriver.SeleniumWebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(SeleniumWebDriverRunner.class)
@Config(exclude = {"browserName=safari"})
public class NewWindowIT {
    @Inject
    private WebDriver driver;

    @Test
    public void openNewWindow() throws Exception {
        driver.get("/open-a-new-window.html");

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
                assertEquals("You Are In The New Window", driver.findElement(By.tagName("h1")).getText());
            } finally {
                driver.close(); // make sure the new window is close
            }

        } finally {
            driver.switchTo().window(originalWindowHandle); // switch back to the original window
        }
    }

    @Test
    public void openWindowUsingName() throws Exception {
        driver.get("http://localhost:8080/open-a-new-window.html");

        try {
            driver.findElement(By.tagName("a")).click();

            driver.switchTo().window("new-window-name");

            assertEquals("You Are In The New Window", driver.findElement(By.tagName("h1")).getText());
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    @Test
    public void openNewWindowUsingStrategy() throws Exception {
        driver.get("/open-a-new-window.html");

        String originalWindowHandle = driver.getWindowHandle();
        driver.findElement(By.tagName("a")).click();

        String windowHandle = driver
                .getWindowHandles()
                .stream()
                .filter(handle -> driver
                        .switchTo()
                        .window(handle)
                        .findElement(By.tagName("h1"))
                        .getText()
                        .equals("You Are In The New Window")) // find a window that has the text we want
                .findFirst()
                .get();

        try {
            driver.switchTo().window(windowHandle);

            assertEquals("You Are In The New Window", driver.findElement(By.tagName("h1")).getText());

            driver.close();
        } finally {
            driver.switchTo().window(originalWindowHandle);
        }
    }
}
