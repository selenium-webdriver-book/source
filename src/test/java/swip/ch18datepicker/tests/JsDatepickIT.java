package swip.ch18datepicker.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch18datepicker.jsdatepick.JsDatepickPage;
import swip.framework.Browser;
import swip.framework.BrowserRunner;
import swip.tests.TestTimer;

import javax.inject.Inject;

import static java.time.Month.APRIL;
import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class JsDatepickIT extends TestTimer {

    @Inject
    private Browser browser;

    @Before
    public void setup() {
        browser.get("/jsdatepick/example-jquery.html");
    }

    @Test
    public void pickADate() {
        new JsDatepickPage(browser) {{
            pick(APRIL, 1, 2030);
            assertEquals("01-APR-2030", getDate());
        }};
    }
}
