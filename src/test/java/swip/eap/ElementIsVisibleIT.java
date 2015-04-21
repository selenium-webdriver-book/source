package swip.eap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SeleniumWebDriverRunner.class)
public class ElementIsVisibleIT {
    @Inject
    private WebDriver driver;

    @Test
    public void visibleElementIsDisplayed() throws Exception {
        driver.get("http://localhost:8080/styled-elements.html");

        assertFalse(driver.findElement(By.id("invisible")).isDisplayed());
        assertTrue(driver.findElement(By.id("visible")).isDisplayed());
    }
}
