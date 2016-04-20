package swip.ch06problems;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swip.framework.WebDriverRunner;
import swip.tests.TestTimer;

import javax.inject.Inject;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(WebDriverRunner.class)
public class TriggerByClassNameIT extends TestTimer {

    @Inject
    private WebDriver browser;

    @Before
    public void setup() {
        browser.get("/react-datepicker.html");
    }

    @Test(expected = NoSuchElementException.class)
    public void failedToLocate() {
        browser.findElement(By.className("ignore-react-onclickoutside"));
    }

    @Test
    public void failedToLocateAgain() {
        WebElement element = browser.findElement(By.className("react-datepicker__input-container"));
        try {
            element.findElement(By.className("ignore-react-onclickoutside"));
            fail();
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }
}
