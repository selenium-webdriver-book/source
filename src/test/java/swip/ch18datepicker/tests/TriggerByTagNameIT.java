package swip.ch18datepicker.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import swip.framework.Browser;
import swip.framework.BrowserRunner;
import swip.tests.TestTimer;

import javax.inject.Inject;

import static swip.locators.react.ReactByClassName.TRIGGER_CONTAINER;
import static swip.locators.TagName.INPUT;

@RunWith(BrowserRunner.class)
public class TriggerByTagNameIT extends TestTimer {

    @Inject
    private Browser browser;

    @Before
    public void setup() {
        browser.get("/react-datepicker.html");
    }

    @Test
    public void locateSuccessfully() {
        browser.findElement(By.className("react-datepicker__input-container"))
            .findElement(By.tagName("input"));
    }

    @Test
    public void trigger() {
        browser.untilFound(TRIGGER_CONTAINER).click(INPUT);
    }
}
