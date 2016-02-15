package swip.ch17datepicker.jquerydatepicker.v2;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
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
        jQueryDatepicker = new JQueryDatepicker(new JQueryCalendar(browser), new JQueryYearControl(browser), new JQueryMonthControl(browser), new JQueryDayPicker(browser));
    }

    @Test
    public void muchBetterDatePicker() {
        jQueryDatepicker.pick(APRIL, 1, 2012);
        assertEquals("04/01/2012", browser.findElement(By.id("datepicker")).getAttribute("value"));
    }
}
