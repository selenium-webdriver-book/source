package swip.ch18datepicker.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.framework.Browser;
import swip.framework.BrowserRunner;
import swip.tests.TestTimer;

import javax.inject.Inject;

import static swip.locators.react.ReactByXpath.TRIGGER_BY;

@RunWith(BrowserRunner.class)
public class TriggerByXpath_v2_IT extends TestTimer {

    @Inject
    private Browser browser;

    @Before
    public void setup() {
        browser.get("/react-datepicker.html");
    }

    @Test
    public void trigger() {
         browser.click(TRIGGER_BY);
    }
}
