package swip.ch17datepicker.jquerydatepicker.v1;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.BrowserRunner;
import swip.framework.TestTimer;

import javax.inject.Inject;

import static java.time.Month.APRIL;
import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class JQueryDatepickerIT extends TestTimer {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Inject
    private Browser browser;

    private JQueryDatepicker jQueryDatepicker;

    @Before
    public void setup() {
        browser.get("/date-picker.html");
        jQueryDatepicker = new JQueryDatepicker(browser);
    }

    @Test
    public void muchBetterDatePicker() {
        jQueryDatepicker.pick(APRIL, 1, 2012);
        assertEquals("04/01/2012", jQueryDatepicker.getDate());
    }
}
