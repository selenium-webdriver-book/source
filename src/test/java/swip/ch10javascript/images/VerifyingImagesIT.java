package swip.ch10javascript.images;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(WebDriverRunner.class)
public class VerifyingImagesIT {
    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/images.html");
    }

    @Test
    public void checkTheImagesAreLoaded() throws Exception {
        assertTrue(isImageLoaded(driver.findElement(By.id("ok"))));
        assertFalse(isImageLoaded(driver.findElement(By.id("broken"))));
    }

    private boolean isImageLoaded(WebElement image) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && " +
                "typeof arguments[0].naturalWidth != 'undefined' && " +
                "arguments[0].naturalWidth > 0", image);
    }
}
