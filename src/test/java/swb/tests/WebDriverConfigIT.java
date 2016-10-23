package swb.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;
import javax.inject.Named;
import java.net.URI;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class WebDriverConfigIT {

    @Inject private DesiredCapabilities desiredCapabilities;
    @Inject
    @Named("baseUrlHttps")
    private URI baseUrlHttps;

    @Test
    public void baseUriIsHttps() throws Exception {
        assertEquals("https", baseUrlHttps.getScheme());
    }

    @Test
    public void firefoxBrowser() throws Exception {
        assertEquals(System.getProperty("webdriver.capabilities.browserName", "chrome"),
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