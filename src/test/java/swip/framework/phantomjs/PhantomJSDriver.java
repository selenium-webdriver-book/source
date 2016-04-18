package swip.framework.phantomjs;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@SuppressWarnings("Convert2Lambda")
public class PhantomJSDriver extends RemoteWebDriver {
    // TODO - update chapter
    private static final Logger LOGGER =
            LoggerFactory.getLogger(PhantomJSDriver.class);
    private final String binaryPath =
            System.getProperty("phantomjs.binary.path", "phantomjs"); // <1>
    private final int port;
    private Process process; // <2>

    public PhantomJSDriver(DesiredCapabilities desiredCapabilities, int port)
            throws MalformedURLException {
        super(new URL("http://localhost:" + port), desiredCapabilities);
        this.port = port;
    }


    @Override
    protected void startClient() {

        LOGGER.info("starting phantomjs on port " + port );

        try {
            process = Runtime.getRuntime()
                    .exec(binaryPath + " --webdriver=" + port); // <3>
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CountDownLatch latch = new CountDownLatch(1); // <4>

        AtomicReference<String> lastLine = new AtomicReference<>();

        new Thread(new Runnable() { // <5>
            @Override
            public void run() {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(process.getErrorStream()));
                String l;
                try {
                    while ((l = in.readLine()) != null) {
                        LOGGER.warn(l);
                        lastLine.set(l);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() { // <6>
            @Override
            public void run() {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));
                String l;
                try {
                    while ((l = in.readLine()) != null) {
                        LOGGER.info(l);
                        lastLine.set(l);
                        if (l.contains("running")) { // <7>
                            latch.countDown();
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        try {
            if (!latch.await(10, TimeUnit.SECONDS)) { // <8>
                throw new IllegalStateException("failed to start phantomjs: " + lastLine);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LOGGER.info("phantomjs running");
    }

    @Override
    protected void stopClient() {
        LOGGER.info("destroying phantomjs");
        process.destroy(); // <9>
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            throw new IllegalStateException("failed to wait for phantomjs", e);
        }
    }
}