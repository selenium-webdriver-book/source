package swip.ap02canvas;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swip.framework.WebDriverRunner;
import swip.framework.WebElementScreenshotTaker;

import javax.inject.Inject;
import java.io.File;

import static org.junit.Assert.assertTrue;

@RunWith(WebDriverRunner.class)
public class CanvasIT {
    @Inject private WebDriver driver;
    @Inject private WebElementScreenshotTaker screenshotTaker;

    @Before
    public void setUp() throws Exception {
        driver.get("/canvas.html");
    }

    @Test
    public void captureCanvas() throws Exception {
        WebElement canvas = driver.findElement(By.tagName("canvas"));

        File destFile = new File("target/canvas.png");
        screenshotTaker.takeScreenshot(driver, canvas, destFile);

        assertTrue(destFile.isFile());

    }
}
