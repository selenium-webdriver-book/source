package swip.ch08unicorns.tooltips;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import swip.framework.Config;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
@Config(exclude = {"browserName=htmlunit", "browserName=safari"})
public class TooltipsIT {

    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/tooltips.html");
    }

    @Test
    public void titleTooltip() throws Exception {
        WebElement element = driver.findElement(By.id("title"));

        new Actions(driver).moveToElement(element).perform();

        String tip = element.getAttribute("title");
        assertEquals("I am the title", tip);
    }

    @Test
    public void javaScriptTooltip() throws Exception {
        WebElement element = driver.findElement(By.id("tooltip"));

        new Actions(driver).moveToElement(element).perform();

        WebElement tipElement = driver.findElement(By.className("tooltip-inner"));
        new WebDriverWait(driver, 1)
                .until((WebDriver webDriver) -> !tipElement.getText().isEmpty());

        assertEquals("I am the tooltip", tipElement.getText());
    }

    @Test
    public void javaScriptPopOver() throws Exception {

        WebElement element = driver.findElement(By.id("popover"));
        element.click();

        String tip = driver
                .findElement(By.id(element.getAttribute("aria-describedBy")))
                .findElement(By.className("popover-content"))
                .getText();
        assertEquals("I am the popover content", tip);
    }
}
