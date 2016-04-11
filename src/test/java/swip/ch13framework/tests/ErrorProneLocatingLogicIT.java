package swip.ch13framework.tests;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swip.framework.TestTimer;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.*;
import static org.openqa.selenium.By.linkText;

@RunWith(WebDriverRunner.class)
public class ErrorProneLocatingLogicIT extends TestTimer {

    @Inject
    private WebDriver driver;

    private static final Log LOG = LogFactory.getLog(ErrorProneLocatingLogicIT.class);

    @Test
    public void errorProneLocatingLogic() {
        try {
            errorProneLocatingLogicWithoutMessage();
            fail("It should pass with an exception.");
        } catch (NoSuchElementException e) {
            LOG.info("======================================");
            LOG.info("The following exception is expected...");
            LOG.info("======================================", e);
            assertTrue(true);
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void errorProneLocatingLogicWithoutMessage() {
        driver.get("/location-chooser.html");
        driver.findElement(linkText("change location")).click();
        WebElement tabMenu = driver.findElement(By.id("location"));
        tabMenu.findElement(linkText("CANADA")).click();
        tabMenu.findElement(linkText("Ontario")).click();                  //<2>
        assertEquals(0, tabMenu.findElements(linkText("Ontario")).size());
        assertEquals("Ontario", driver
            .findElement(By.cssSelector(".tools-location strong"))
            .getText());
    }
}
