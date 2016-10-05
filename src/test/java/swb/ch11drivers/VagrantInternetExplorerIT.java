package swb.ch11drivers;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

@Ignore
public class VagrantInternetExplorerIT {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new RemoteWebDriver(
                new URL("http://localhost:14444/wd/hub"), DesiredCapabilities.internetExplorer()
        );
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void openGoogle() throws Exception {
        driver.get("http://www.google.com");
    }
}
