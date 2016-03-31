package swip.ch18datepicker.jquery.v5;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch18datepicker.framework.Browser;
import swip.ch18datepicker.framework.BrowserRunner;
import swip.framework.TestTimer;

import javax.inject.Inject;

import static java.time.Month.APRIL;
import static org.junit.Assert.assertEquals;


@RunWith(BrowserRunner.class)
public class JQueryDatepickerIT extends TestTimer{

    @Inject
    private Browser browser;
    private JQueryDatepickerPage jQueryDatePickerPage;

    @Before
    public void setup() {
        browser.get("/datepicker.html");
        jQueryDatePickerPage = new JQueryDatepickerPage(browser);
    }

    @Test
    public void pickADate() {
        jQueryDatePickerPage.pick(APRIL, 1, 2014);
        assertEquals("04/01/2014", jQueryDatePickerPage.getDate());
    }

}
