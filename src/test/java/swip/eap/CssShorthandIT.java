package swip.eap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swip.junit.Config;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(SeleniumWebDriverRunner.class)
@Config(exclude = {"browserName=htmlunit", "browserName=firefox"})
public class CssShorthandIT {
    @Inject
    private WebDriver driver;

    @Test
    public void cssShortHandBackground() throws Exception {
        driver.get("http://localhost:8080/styled-elements.html");

        WebElement div = driver.findElement(By.id("shorthand"));

        assertEquals("rgba(0, 0, 0, 0) none repeat scroll 0% 0% / auto padding-box border-box", div.getCssValue("background"));
    }
}
