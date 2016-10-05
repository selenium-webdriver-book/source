package swb.ch11drivers.mobileemulation;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import swb.ch11drivers.ChromeDriverBinarySupplier;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Ignore("Chrome not supported on CircleCI")
public class ChromeMobileEmulationDeviceAttributesIT {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", String.valueOf(new ChromeDriverBinarySupplier().get(Paths.get("target"))));

        Map<String, Object> deviceMetrics = new HashMap<>();

        deviceMetrics.put("width", 768);
        deviceMetrics.put("height", 1024);
        deviceMetrics.put("pixelRatio", 2);

        Map<String, Object> mobileEmulation = new HashMap<>();

        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (iPad; CPU OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53");

        Map<String, Object> chromeOptions = new HashMap<>();

        chromeOptions.put("mobileEmulation", mobileEmulation);

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        driver = new ChromeDriver(capabilities);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void smokeTest() throws Exception {

        driver.get("http://localhost:8080");

        Thread.sleep(10000);

    }
}
