package swip.ch18datepicker.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch18datepicker.bootstrap.v2.BootstrapDatepickerPage;
import swip.framework.Browser;
import swip.framework.BrowserRunner;
import swip.tests.TestTimer;

import javax.inject.Inject;

import static java.time.Month.APRIL;
import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class BootstrapDatepicker_v2_IT extends TestTimer {

    @Inject
    private Browser browser;

    @Before
    public void setup() {
        browser.get("/bootstrap-datepicker.html");
    }

    @Test
    public void pickADate() {
        new BootstrapDatepickerPage(browser) {{
            pick(APRIL, 1, 2015);
            assertEquals("01-04-2015", getDate());
        }};
    }
}
