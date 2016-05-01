package swip.ch13framework.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.TimeoutException;
import swip.ch13framework.v4.Browser;
import swip.ch13framework.v4.BrowserRunner;
import swip.locators.react.ReactByClassName;
import swip.tests.TestTimer;

import javax.inject.Inject;

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
        browser.untilFound(ReactByClassName.TRIGGER_CONTAINER)
            .click(ReactByClassName.TRIGGER_BY);
    }
}