package swip.ch17datepicker;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.BrowserRunner;
import swip.ch15pageflow.framework.Element;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class NaiveDatepickerIT {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Inject private Browser browser;

    @Test
    public void triggerDatepicker() {
        browser.get("/date-picker.html");
        Element trigger = browser.untilFound(() -> By.id("datepicker"));
        trigger.click();
    }


    @Test
    public void naiveDatePicker() {
        NaiveDatepicker datepicker = new NaiveDatepicker(browser);

        Date date = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            date = dateFormat.parse("04/01/2012");
        } catch(ParseException e) {
            e.printStackTrace();
        }

        assertEquals("04/01/2012", datepicker.pickDate(date));

    }


    @Test
    public void betterDatePicker() {
        BetterJQueryDatepicker datepicker = new BetterJQueryDatepicker(browser);

        Date date = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            date = dateFormat.parse("04/01/2012");
        } catch(ParseException e) {
            e.printStackTrace();
        }

        assertEquals("04/01/2012", datepicker.pickDate(date));

    }


}
