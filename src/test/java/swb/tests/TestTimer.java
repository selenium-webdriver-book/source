package swb.tests;

import org.apache.commons.lang3.time.StopWatch;       //<1>
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;

public class TestTimer {

    private static final Log LOG = LogFactory.getLog(TestTimer.class);

    private StopWatch stopWatch = new StopWatch();

    @Before
    public void startStopWatch() {
        stopWatch.start();                  //<2>
    }

    @After
    public void print() {
        LOG.info("");
        LOG.info("==========================");
        LOG.info("Taken " + stopWatch);          //<3>
        LOG.info("==========================");
    }

}
