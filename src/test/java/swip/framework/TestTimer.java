package swip.framework;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class TestTimer {

    private StopWatch stopWatch = new StopWatch();

    @Before
    public void startStopWatch() {
        stopWatch.start();
    }

    @After
    public void print() {
        System.out.println();
        System.out.println("==========================");
        System.out.println("Taken " + stopWatch);
        System.out.println("==========================");
    }

}
