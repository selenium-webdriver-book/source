package swb.ch18datepicker.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swb.ch18datepicker.react.ReactDatepickerPage;
import swb.framework.Browser;
import swb.framework.BrowserRunner;
import swb.tests.TestTimer;

import javax.inject.Inject;

import static java.time.Month.APRIL;
import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class ReactDatepickerIT extends TestTimer {

    @Inject
    private Browser browser;

    @Before
    public void setup() {
        browser.get("/react-datepicker.html");
    }

    @Test
    public void pickADate() {
        new ReactDatepickerPage(browser) {{
            pick(APRIL, 1, 2015);
            assertEquals("04/01/2015", getDate());
        }};
    }
}
