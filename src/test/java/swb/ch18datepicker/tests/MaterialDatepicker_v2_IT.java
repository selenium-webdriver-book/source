package swb.ch18datepicker.tests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import swb.ch18datepicker.material_ui.v2.MaterialDatepickerPage;
import swb.framework.Browser;
import swb.framework.BrowserRunner;
import swb.tests.TestTimer;

import javax.inject.Inject;

import static java.time.Month.APRIL;
import static org.junit.Assert.assertEquals;

@Ignore("The transition effect will cause java.lang.ArrayIndexOutOfBoundsException: 1")
@RunWith(BrowserRunner.class)
public class MaterialDatepicker_v2_IT extends TestTimer {

    @Inject
    private Browser browser;

    @Before
    public void setup() {
        browser.get("/material-ui.html#/components/date-picker");
    }

    @Test
    public void pickADate() {
        new MaterialDatepickerPage(browser) {{
            pick(APRIL, 1, 2015);
            assertEquals("4/1/2015", super.getDate());
        }};
    }
}
