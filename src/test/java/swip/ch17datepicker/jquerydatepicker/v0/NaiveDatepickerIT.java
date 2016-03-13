package swip.ch17datepicker.jquerydatepicker.v0;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import swip.ch15pageflow.v2.framework.Browser;
import swip.ch15pageflow.v2.framework.BrowserRunner;
import swip.framework.TestTimer;

import javax.inject.Inject;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class NaiveDatepickerIT extends TestTimer {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Inject
    private Browser browser;

    private Calendar calendar;

    private NaiveDatepicker naiveDatepicker;

    @Before
    public void setup() {
        browser.get("/datepicker.html");
        naiveDatepicker = new NaiveDatepicker(browser);

        calendar = Calendar.getInstance();
        calendar.set(2014, 3, 1);
    }

    @Test
    public void naiveDatePicker() {

        naiveDatepicker.pickDate(calendar);
        assertEquals("04/01/2014", naiveDatepicker.getDate());
    }
}
