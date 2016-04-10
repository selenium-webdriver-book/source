package swip.ch18datepicker.tests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.BrowserRunner;
import swip.ch18datepicker.jquery.v3.JQueryDatepickerPage;
import swip.framework.TestTimer;

import javax.inject.Inject;

import static java.time.Month.APRIL;
import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class JQueryDatepickerV3IT extends TestTimer {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Inject
    private Browser browser;
    private JQueryDatepickerPage jQueryDatePickerPage;

    @Before
    public void setup() {
        browser.get("/datepicker.html");
    }

    @Test
    public void pickADate() {
        jQueryDatePickerPage = new JQueryDatepickerPage(browser);
        jQueryDatePickerPage.pick(APRIL, 1, 2018);
        assertEquals("04/01/2018", jQueryDatePickerPage.getDate());
    }

}
