package swip.ch17datepicker.bootstrapdatepicker;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.BrowserRunner;

import javax.inject.Inject;

import static java.time.Month.APRIL;
import static org.junit.Assert.assertEquals;


@RunWith(BrowserRunner.class)
public class BootstrapDatepickerIT {

    @Inject
    private Browser browser;

    private StopWatch stopWatch = new StopWatch();
    private BootstrapDatepickerPage bootstrapDatepickerPage;

    @Before
    public void startStopWatch() {
        stopWatch.start();
    }

    @After
    public void print() {
        System.out.println("Taken " + stopWatch);
    }

    @Before
    public void setup() {
        browser.get("/bootstrap-date-picker.html");
        bootstrapDatepickerPage = new BootstrapDatepickerPage(browser);
    }

    @Test
    public void pickADate() {
        bootstrapDatepickerPage.pick(APRIL, 1, 2012);
        assertEquals("01-04-2012", bootstrapDatepickerPage.getDate());
    }

}
