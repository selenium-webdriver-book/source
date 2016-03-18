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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class BrokenDatepickerIT extends TestTimer {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Inject
    private Browser browser;

    private Date date;

    private BrokenDatepicker naiveDatepicker;

    @Before
    public void setup() {
        browser.get("/datepicker.html");
        naiveDatepicker = new BrokenDatepicker(browser);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            date = dateFormat.parse("04/01/2014");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void brokenNaiveDatePicker() {
        naiveDatepicker.pickDate(date);
        assertEquals("04/02/2014",naiveDatepicker.getDate());
    }

}
