package swip.ch10drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

@Ignore("only runs with Appium")
public class AppiumAndroidIT {
    private AppiumDriver<MobileElement> driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = DesiredCapabilities.android();

        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
        //capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.3");
        //capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");

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
    public void openGoogle() throws Exception {
        driver.get("http://www.google.com");

        MobileElement element = driver.findElement(By.xpath("//button"));

        element.click();
    }
}
