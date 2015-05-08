package swip.ch04examiningapage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swip.ch07managingwebdriver.Config;
import swip.ch07managingwebdriver.SeleniumWebDriverRunner;

import javax.inject.Inject;
import java.net.URI;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SeleniumWebDriverRunner.class)
@Config(exclude = "browserName=htmlunit")
public class VerifyingImagesIT<W extends WebDriver & JavascriptExecutor> {
    @Inject
    private W driver;
    @Inject
    private URI baseUrl;

    @Test
    public void checkTheImagesAreLoaded() throws Exception {
        driver.get(baseUrl + "/images.html");

        assertTrue(isImageLoaded(driver.findElement(By.id("ok"))));
        assertFalse(isImageLoaded(driver.findElement(By.id("broken"))));
    }

    private boolean isImageLoaded(WebElement image) {
        return (boolean) driver.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", image);
    }
}
