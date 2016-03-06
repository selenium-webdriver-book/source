package swip.ch17datepicker.jquerydatepicker.v5;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch15pageflow.framework.v2.Browser;
import swip.ch15pageflow.framework.v2.BrowserRunner;
import swip.framework.TestTimer;

import javax.inject.Inject;

import static java.time.Month.APRIL;
import static org.junit.Assert.assertEquals;


@RunWith(BrowserRunner.class)
public class JQueryDatePickerIT extends TestTimer{

    @Inject
    private Browser browser;
    private JQueryDatePickerPage jQueryDatePickerPage;

    @Before
    public void setup() {
        browser.get("/datepicker.html");
        jQueryDatePickerPage = new JQueryDatePickerPage(browser);
    }

    @Test
    public void pickADate() {
        jQueryDatePickerPage.pick(APRIL, 1, 2012);
        assertEquals("04/01/2012", jQueryDatePickerPage.getDate());
    }

}
