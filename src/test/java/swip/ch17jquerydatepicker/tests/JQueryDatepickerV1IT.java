package swip.ch17jquerydatepicker.tests;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.BrowserRunner;
import swip.ch17jquerydatepicker.jquery.v1.JQueryDatepicker;
import swip.framework.TestTimer;

import javax.inject.Inject;

import static java.time.Month.APRIL;
import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class JQueryDatepickerV1IT extends TestTimer {

    @Inject private Browser browser;

    private JQueryDatepicker jQueryDatepicker;

    @Before public void setup() {
        browser.get("/datepicker.html");
        jQueryDatepicker = new JQueryDatepicker(browser);
    }

    @Test public void muchBetterDatePicker() {
        jQueryDatepicker.pick(APRIL, 1, 2014);
        assertEquals("04/01/2014", jQueryDatepicker.getDate());
    }
}
