package swip.ch04examiningapage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch07managingwebdriver.injecting.WebDriverRunner;

import javax.inject.Inject;
import java.net.URI;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(WebDriverRunner.class)
public class ElementIsVisibleIT {
    @Inject
    private WebDriver driver;
    @Inject
    private URI baseUrl;

    @Test
    public void visibleElementIsDisplayed() throws Exception {
        driver.get(baseUrl + "/styled-elements.html");

        assertFalse(driver.findElement(By.id("invisible")).isDisplayed());
        assertTrue(driver.findElement(By.id("visible")).isDisplayed());
    }
}
