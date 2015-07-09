package swip.ch10drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class AppiumIPhoneIT {
    private AppiumDriver<MobileElement> driver; // <1>

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = DesiredCapabilities.iphone();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");  // <2>
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.3");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "safari"); // <3>

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
    public void openGoogle() throws Exception {
        driver.get("http://www.google.com");

        MobileElement element = driver.findElement(By.xpath("//button"));

        element.click();
    }
}
