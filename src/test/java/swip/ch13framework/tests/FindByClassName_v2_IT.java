package swip.ch13framework.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.TimeoutException;
import swip.ch13framework.v4.Browser;
import swip.ch13framework.v4.BrowserRunner;
import swip.tests.TestTimer;

import javax.inject.Inject;

import static swip.locators.react.ReactByClassName.TRIGGER_BY;
import static swip.locators.react.ReactByClassName.TRIGGER_CONTAINER;

@RunWith(BrowserRunner.class)
public class FindByClassName_v2_IT extends TestTimer {

    @Inject
    private Browser browser;

    @Before
    public void setup() {
        browser.get("/react-datepicker.html");
    }

    @Test(expected = TimeoutException.class)
    public void trigger() {
        browser.await(TRIGGER_CONTAINER).click(TRIGGER_BY);
    }
}