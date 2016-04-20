package swip.ch18datepicker.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.framework.Browser;
import swip.framework.BrowserRunner;
import swip.locators.react.ReactByXpath;
import swip.tests.TestTimer;

import javax.inject.Inject;

@RunWith(BrowserRunner.class)
public class FindByXpath_v2_IT extends TestTimer {

    @Inject
    private Browser browser;

    @Before
    public void setup() {
        browser.get("/react-datepicker.html");
    }

    @Test
    public void trigger() {
         browser.click(ReactByXpath.TRIGGER_BY);
    }
}
