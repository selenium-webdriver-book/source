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
public class ChromeMobileEmulationKnownDeviceIT {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", String.valueOf(new ChromeDriverBinarySupplier().get(Paths.get("target"))));

        Map<String, String> mobileEmulation = new HashMap<>();

        mobileEmulation.put("deviceName", "Apple iPad");

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
