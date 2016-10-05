package swb.ch18datepicker.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swb.framework.Browser;
import swb.framework.BrowserRunner;
import swb.locators.react.ReactByXpath;
import swb.tests.TestTimer;

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
