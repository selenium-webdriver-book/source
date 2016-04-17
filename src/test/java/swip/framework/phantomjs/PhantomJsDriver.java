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

@SuppressWarnings("Convert2Lambda")
public class PhantomJsDriver extends RemoteWebDriver {
    private static final String BINARY_PATH = System.getProperty("phantomjs.binary.path", "phantomjs");
    private static final int PORT = Integer.parseInt(System.getProperty("phantomjs.PORT", "5555"));
    private static final Logger LOGGER = LoggerFactory.getLogger(PhantomJsDriver.class);
    private Process process;

    public PhantomJsDriver(DesiredCapabilities desiredCapabilities) throws MalformedURLException {
        super(new URL("http://localhost:" + PORT), desiredCapabilities);
    }

    @Override
    protected void startClient() {

        try {
            process = Runtime.getRuntime().exec(BINARY_PATH + " --webdriver=" + PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CountDownLatch latch = new CountDownLatch(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String l;
                try {
                    while ((l = err.readLine()) != null) {
                        LOGGER.warn(l);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader err = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String l;
                try {
                    while ((l = err.readLine()) != null) {
                        if (l.contains("running")) {
                            latch.countDown();
                        }
                        LOGGER.info(l);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();


        if (!process.isAlive()) {
            throw new IllegalStateException("failed to start phantomjs");
        }

        try {
            latch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void stopClient() {
        process.destroy();
    }
}
