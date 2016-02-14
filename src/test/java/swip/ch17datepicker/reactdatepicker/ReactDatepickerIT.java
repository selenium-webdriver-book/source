package swip.ch17datepicker.reactdatepicker;

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
public class ReactDatepickerIT {

    @Inject
    private Browser browser;

    private StopWatch stopWatch = new StopWatch();
    private ReactDatepickerPage reactDatepickerPage;

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
        browser.get("http://hacker0x01.github.io/react-datepicker/");
        reactDatepickerPage = new ReactDatepickerPage(browser);
    }

    @Test
    public void pickADate() {
        reactDatepickerPage.pick(APRIL, 1, 2012);
        assertEquals("2012-04-01", reactDatepickerPage.getDate());
    }

}
