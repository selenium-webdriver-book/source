package swip.ch17reactdatepicker.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch17reactdatepicker.react.v1.ReactDatepicker;
import swip.framework.Browser;
import swip.framework.BrowserRunner;
import swip.tests.TestTimer;

import javax.inject.Inject;

import static java.time.Month.APRIL;
import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class ReactDatepickerV1IT extends TestTimer {

    @Inject
    private Browser browser;
    private ReactDatepicker reactDatepicker;

    @Before
    public void setup() {
        browser.get("/react-datepicker.html");
        reactDatepicker = new ReactDatepicker(browser);
    }

    @Test
    public void muchBetterDatePicker() {
        reactDatepicker.pick(APRIL, 1, 2014);
        assertEquals("04/01/2014", reactDatepicker.getDate());
    }
}
