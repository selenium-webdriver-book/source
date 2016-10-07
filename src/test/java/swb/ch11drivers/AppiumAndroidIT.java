package swb.ch11drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

import static org.junit.Assert.assertEquals;

@Ignore("only runs with Appium")
public class AppiumAndroidIT {
    private AppiumDriver<MobileElement> driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "browser");

        driver = new AndroidDriver<>(
                new URL("http://127.0.0.1:4723/wd/hub"),
                capabilities
        );
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void openHomepage() throws Exception {
        driver.get("http://GL04321M.lan:8080");

        driver.findElement(By.tagName("a"));

        assertEquals("Selenium WebDriver Book - Index", driver.getTitle());
    }
}
