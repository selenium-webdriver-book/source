package swb.ch17jquerydatepicker.tests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import swb.ch17jquerydatepicker.jquery.v2.*;
import swb.framework.Browser;
import swb.framework.BrowserRunner;
import swb.tests.TestTimer;

import javax.inject.Inject;

import static java.time.Month.APRIL;
import static org.junit.Assert.assertEquals;
import static swb.locators.jquery.JQueryById.TRIGGER_BY;

@RunWith(BrowserRunner.class)
public class JQueryDatepicker_v2_IT extends TestTimer {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Inject
    private Browser browser;
    private JQueryDatepicker jQueryDatepicker;

    @Before
    public void setup() {
        browser.get("/datepicker.html");
        jQueryDatepicker = new JQueryDatepicker(new JQueryCalendar(browser), new JQueryYearPicker(browser), new JQueryMonthPicker(browser), new JQueryDayPicker(browser));
    }

    @Test
    public void shouldPickaDate() {
        jQueryDatepicker.pick(APRIL, 1, 2014);
        assertEquals("04/01/2014", browser.getInputText(TRIGGER_BY));
    }
}
