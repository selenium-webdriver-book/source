package swip.ch17datepicker.jquerydatepicker.v0;


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
        NaiveDatepicker naiveDatepicker = new NaiveDatepicker(browser);
        naiveDatepicker.pickDate(date);
        assertEquals("04/01/2012",naiveDatepicker.getDate());
    }

    @Test
    public void betterDatePicker() {
        BetterJQueryDatepicker betterJQueryDatepicker = new BetterJQueryDatepicker(browser);
        betterJQueryDatepicker.pickDate(date);
        assertEquals("04/01/2012", betterJQueryDatepicker.getDate());
    }

    @Test
    public void muchBetterDatePicker() {
        MuchBetterJQueryDatepicker muchBetterJQueryDatepicker = new MuchBetterJQueryDatepicker(browser);
        muchBetterJQueryDatepicker.pickDate(date);
        assertEquals("04/01/2012", muchBetterJQueryDatepicker.getDate());
    }
}
