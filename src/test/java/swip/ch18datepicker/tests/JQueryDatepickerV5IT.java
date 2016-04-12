package swip.ch18datepicker.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.framework.Browser;
import swip.framework.BrowserRunner;
import swip.ch18datepicker.jquery.v5.JQueryDatepickerPage;
import swip.tests.TestTimer;

import javax.inject.Inject;

import static java.time.Month.APRIL;
import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class JQueryDatepickerV5IT extends TestTimer {

    @Inject
    private Browser browser;
    private JQueryDatepickerPage jQueryDatePickerPage;

    @Before
    public void setup() {
        browser.get("/datepicker.html");
    }

    @Test
    public void pickADate() {
        jQueryDatePickerPage = new JQueryDatepickerPage(browser);
        jQueryDatePickerPage.pick(APRIL, 1, 2014);
        assertEquals("04/01/2014", jQueryDatePickerPage.getDate());
    }
}
