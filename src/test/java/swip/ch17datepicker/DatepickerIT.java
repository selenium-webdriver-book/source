package swip.ch17datepicker;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.BrowserRunner;
import swip.ch15pageflow.framework.Element;
import swip.framework.TestTimer;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class DatepickerIT extends TestTimer {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Inject
    private Browser browser;

    private Date date;

    @Before
    public void setup() {
        browser.get("/datepicker.html");
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            date = dateFormat.parse("04/01/2012");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void triggerDatepicker() {
        Element trigger = browser.untilFound(() -> By.id("datepicker"));
        trigger.click();
    }

    @Test
    public void naiveDatePicker() {
        assertEquals("04/01/2012", new NaiveDatepicker(browser).pickDate(date));
    }

    @Test
    public void betterDatePicker() {
        assertEquals("04/01/2012", new BetterJQueryDatepicker(browser).pickDate(date));
    }

    @Test
    public void muchBetterDatePicker() {
        assertEquals("04/01/2012", new MuchBetterJQueryDatepicker(browser).pickDate(date));
    }
}
