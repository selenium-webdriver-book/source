package swip.ch17jquerydatepicker.tests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import swip.ch17jquerydatepicker.jquery.v2.*;
import swip.framework.Browser;
import swip.framework.BrowserRunner;
import swip.tests.TestTimer;

import javax.inject.Inject;

import static java.time.Month.APRIL;
import static org.junit.Assert.assertEquals;
import static swip.locators.JQueryById.TRIGGER_BY;

@RunWith(BrowserRunner.class)
public class JQueryDatepickerV2IT extends TestTimer {

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
