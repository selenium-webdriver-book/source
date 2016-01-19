package swip.ch09unicorns.tooltips;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class EncapsulatedTooltipsIT {

    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/tooltips.html");
    }

    @Test
    public void titleTooltip() throws Exception {
        String tip = Tooltip.tip(driver, By.id("title"));
        assertEquals("I am the title", tip);
    }

    @Test
    public void javaScriptTooltip() throws Exception {
        String tip = Tooltip.tip(driver, By.id("tooltip"));
        assertEquals("I am the tooltip", tip);
    }

    @Test
    public void javaScriptPopOver() throws Exception {
        String tip = Tooltip.tip(driver, By.id("popover"));
        assertEquals("I am the popover content", tip);
    }
}
