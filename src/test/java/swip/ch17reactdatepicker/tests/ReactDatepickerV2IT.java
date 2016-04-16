package swip.ch17reactdatepicker.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch17reactdatepicker.react.v2.*;
import swip.framework.Browser;
import swip.framework.BrowserRunner;
import swip.tests.TestTimer;

import javax.inject.Inject;

import static java.time.Month.APRIL;
import static org.junit.Assert.assertEquals;
import static swip.locators.ReactByXpath.TRIGGER_BY;


@RunWith(BrowserRunner.class)
public class ReactDatepickerV2IT extends TestTimer {

    @Inject
    private Browser browser;
    private ReactDatepicker reactDatepicker;

    @Before
    public void setup() {
        browser.get("/react-datepicker.html");
        reactDatepicker = new ReactDatepicker(new ReactCalendar(browser), new ReactYearPicker(browser), new ReactMonthPicker(browser), new ReactDayPicker(browser));
    }

    @Test
    public void shouldPickaDate() {
        reactDatepicker.pick(APRIL, 1, 2014);
        assertEquals("04/01/2014", browser.getInputText(TRIGGER_BY));
    }
}
