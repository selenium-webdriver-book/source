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
@Config(exclude = "browserName=htmlunit") // he thinks it is red
public class VerifyingStylesIT {

    @Inject
    private WebDriver driver;

    @Test
    public void elementHasRedText() throws Exception {
        driver.get("/styled-elements.html");

        WebElement element = driver.findElement(By.id("red"));

        assertEquals("rgba(255, 0, 0, 1)", element.getCssValue("color"));
    }

    @Test
    public void cssShortHand() throws Exception {
        driver.get("/styled-elements.html");

        WebElement div = driver.findElement(By.id("shorthand"));

        assertEquals("1px solid rgb(0, 0, 0)", div.getCssValue("border"));

        assertEquals("rgb(0, 0, 0)", div.getCssValue("border-color"));
        assertEquals("solid", div.getCssValue("border-style"));
        assertEquals("1px", div.getCssValue("border-width"));
    }
}
