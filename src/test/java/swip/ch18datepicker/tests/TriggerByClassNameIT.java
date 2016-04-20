package swip.ch18datepicker.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import swip.framework.Browser;
import swip.framework.BrowserRunner;
import swip.locators.react.ReactByClassName;
import swip.tests.TestTimer;

import javax.inject.Inject;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static swip.locators.react.ReactByClassName.TRIGGER_CONTAINER;

@RunWith(BrowserRunner.class)
public class TriggerByClassNameIT extends TestTimer {

    @Inject
    private Browser browser;

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

    @Test(expected = TimeoutException.class)
    public void trigger() {
        browser.untilFound(TRIGGER_CONTAINER).click(ReactByClassName.TRIGGER_BY);
    }
}
