package swip.framework;

import org.apache.commons.lang3.time.StopWatch;       //<1>
import org.junit.After;
import org.junit.Before;

public class TestTimer {

    private StopWatch stopWatch = new StopWatch();

    @Before
    public void startStopWatch() {
        stopWatch.start();                  //<2>
    }

    @After
    public void print() {
        System.out.println();
        System.out.println("==========================");
        System.out.println("Taken " + stopWatch);          //<3>
        System.out.println("==========================");
    }

}
