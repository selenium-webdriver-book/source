package swb.ch08windows.windows;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class NewWindowIT {
    @Inject
    private WebDriver driver;

    @Test
    public void openNewWindow() throws Exception {
        driver.get("/open-a-new-window.html");

        String originalWindowHandle = driver.getWindowHandle(); // store the original window handle
        try { // wrap the new window operations in a try/finally block
            driver.findElement(By.tagName("a")).click(); // execute an operation to open a new window

            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(originalWindowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }

            try {
                // ... perform operations on the new window
                assertEquals("You Are In The New Window", driver.findElement(By.tagName("h1")).getText());
            } finally {
                driver.close(); // make sure the new window is close
            }

        } finally {
            driver.switchTo().window(originalWindowHandle); // switch back to the original window
        }
        assertEquals("Open A New Window", driver.findElement(By.tagName("h1")).getText());
    }

    @Test
    public void openWindowUsingName() throws Exception {
        driver.get("/open-a-new-window.html");
        String originalWindowHandle = driver.getWindowHandle();

        try {
            driver.findElement(By.tagName("a")).click();

            driver.switchTo().window("new-window-name");

            try {
                assertEquals("You Are In The New Window", driver.findElement(By.tagName("h1")).getText());
            } finally {
                driver.close();
            }
        } finally {
            driver.switchTo().window(originalWindowHandle);
        }
        assertEquals("Open A New Window", driver.findElement(By.tagName("h1")).getText());
    }

    @Test
    public void openNewWindowUsingStrategy() throws Exception {
        driver.get("/open-a-new-window.html");

        String originalWindowHandle = driver.getWindowHandle();

        driver.findElement(By.tagName("a")).click();

        try {
            for (String windowHandle : driver.getWindowHandles()) {
                driver.switchTo().window(windowHandle);
                if (driver.findElement(By.tagName("h1")).getText()
                        .equals("You Are In The New Window")) {
                    break;
                }
            }

            assertEquals("You Are In The New Window", driver.findElement(By.tagName("h1")).getText());

            driver.close();
        } finally {
            driver.switchTo().window(originalWindowHandle);
        }
        assertEquals("Open A New Window", driver.findElement(By.tagName("h1")).getText());
    }
}
