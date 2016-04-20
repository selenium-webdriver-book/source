package swip.ch06problems;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import swip.framework.Browser;
import swip.framework.BrowserRunner;
import swip.framework.WebDriverRunner;
import swip.tests.TestTimer;

import javax.inject.Inject;

import static swip.locators.TagName.INPUT;
import static swip.locators.react.ReactByClassName.TRIGGER_CONTAINER;

@RunWith(WebDriverRunner.class)
public class TriggerByTagNameIT extends TestTimer {

    @Inject
    private WebDriver browser;

    @Before
    public void setup() {
        browser.get("/react-datepicker.html");
    }

    @Test
    public void locateSuccessfully() {
        browser.findElement(By.className("react-datepicker__input-container"))
            .findElement(By.tagName("input")).click();
    }

}
