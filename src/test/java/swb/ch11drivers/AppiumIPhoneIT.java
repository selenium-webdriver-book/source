package swb.ch11drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

import static org.junit.Assert.assertEquals;

@Ignore("only runs with Appium")
public class AppiumIPhoneIT {
    private AppiumDriver<MobileElement> driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();


        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.2");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "safari");

        driver = new IOSDriver<>(
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
