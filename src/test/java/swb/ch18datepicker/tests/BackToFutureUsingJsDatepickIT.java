package swb.ch18datepicker.tests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import swb.ch18datepicker.jsdatepick.JsDatepickPage;
import swb.ch18datepicker.jsdatepick.JsDatepickPageLambda;
import swb.framework.Browser;
import swb.framework.BrowserRunner;
import swb.tests.TestTimer;

import javax.inject.Inject;

import static java.time.Month.*;
import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
@Ignore("failed in Selenium 3 upgrade")
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
