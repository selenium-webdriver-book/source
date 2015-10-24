package swip.ch09unicorns.actionchains;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class LazyActionChainIT {
    @Inject
    private WebDriver driver;

    @Test(expected = StaleElementReferenceException.class)
    public void staleElementProblem() throws Exception {

        driver.get("/stale-elements.html");

        WebElement button = driver.findElement(By.id("button"));

        new Actions(driver)
                .click(button)
                .click(button)
                .perform();
    }

    @Test
    public void lazyActionChain() throws Exception {

        driver.get("/stale-elements.html");

        WebElement button = new LazyElement(driver, By.id("button"));

        new Actions(driver)
                .click(button)
                .click(button)
                .perform();

        assertEquals("Click This Button", button.getText());
    }
}
