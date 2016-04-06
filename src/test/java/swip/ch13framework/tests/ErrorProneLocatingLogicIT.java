package swip.ch13framework.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import swip.framework.TestTimer;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.openqa.selenium.By.linkText;

@RunWith(WebDriverRunner.class)
public class ErrorProneLocatingLogicIT extends TestTimer {

    @Inject
    private WebDriver driver;

    @Test
    public void errorProneLocatingLogic() {
        try {
            driver.get("/location-chooser.html");
            driver.findElement(linkText("change location")).click();
            WebElement tabMenu = driver.findElement(By.id("location"));
            tabMenu.findElement(linkText("CANADA")).click();
            tabMenu.findElement(linkText("Ontario")).click();                  //<2>
            assertEquals(0, tabMenu.findElements(linkText("Ontario")).size());
            assertEquals("Ontario", driver
                .findElement(By.cssSelector(".tools-location strong"))
                .getText());
            fail("It should pass with an exception.");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            assertTrue(true);
        }
    }
}
