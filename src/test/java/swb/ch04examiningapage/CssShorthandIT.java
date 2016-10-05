package swb.ch04examiningapage;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;
import java.net.URI;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class CssShorthandIT {
    @Inject
    private WebDriver driver;
    @Inject
    private URI baseUrl;

    @Test
    @Ignore("this does work in Firefox")
    public void cssShortHandBackground() throws Exception {
        driver.get(baseUrl + "/styled-elements.html");

        WebElement div = driver.findElement(By.id("shorthand"));

        assertEquals("rgba(0, 0, 0, 0) none repeat scroll 0% 0% / auto padding-box border-box", div.getCssValue("background"));
    }
}
