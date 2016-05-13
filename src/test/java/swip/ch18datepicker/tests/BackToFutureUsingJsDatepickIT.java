package swip.ch18datepicker.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch18datepicker.jsdatepick.JsDatepickPage;
import swip.ch18datepicker.jsdatepick.JsDatepickPageLambda;
import swip.framework.Browser;
import swip.framework.BrowserRunner;
import swip.tests.TestTimer;

import javax.inject.Inject;

import static java.time.Month.*;
import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class BackToFutureUsingJsDatepickIT extends TestTimer {

    @Inject
    private Browser browser;

    @Before
    public void setup() {
        browser.get("/jsdatepick/example-jquery.html");
    }

    @Test
    public void backToFuture1() {
        new JsDatepickPage(browser) {{
            pick(NOVEMBER, 5, 1955);
            assertEquals("05-NOV-1955", super.getDate());
        }};
    }

    @Test
    public void backToFuture2() {
        new JsDatepickPage(browser) {{
            pick(OCTOBER, 21, 2015);
            assertEquals("21-OCT-2015", super.getDate());
        }};
    }

    @Test
    public void backToFuture3() {
        new JsDatepickPageLambda(browser) {{
            pick(SEPTEMBER, 2, 1885);
            assertEquals("02-SEP-1885", super.getDate());
        }};
    }
}
