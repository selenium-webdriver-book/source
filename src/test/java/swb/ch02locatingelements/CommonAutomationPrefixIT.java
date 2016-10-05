package swb.ch02locatingelements;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class CommonAutomationPrefixIT {

    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/shopping-cart.html");
    }

    @Test
    public void commonPrefix() throws Exception {
        assertEquals("1",
                driver.findElement(By.className(String.format("wd-cart-item-%d", 0)))
                        .getAttribute("value"));
    }

    @Test
    public void commonPrefixFactory() throws Exception {
        assertEquals("1",
                driver.findElement(ElementBy.automationId(String.format("cart-item-%d", 0)))
                        .getAttribute("value"));
    }
}
