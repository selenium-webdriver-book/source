package swip.framework;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class WebDriverConfigIT {

    @Inject private DesiredCapabilities desiredCapabilities;

    @Test
    public void firefoxBrowser() throws Exception {
        assertEquals(System.getProperty("webdriver.capabilities.browserName", "firefox"),
                desiredCapabilities.getBrowserName());
    }

    @Test
    public void acceptSslCerts() throws Exception {
        assertEquals(true, desiredCapabilities.getCapability(CapabilityType.ACCEPT_SSL_CERTS));
    }

    @Test
    public void anyPlatform() throws Exception {
        assertEquals(Platform.ANY, desiredCapabilities.getCapability(CapabilityType.PLATFORM));
    }

}