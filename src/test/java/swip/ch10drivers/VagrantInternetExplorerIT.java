package swip.ch10drivers;

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

    @Test
    public void openGoogle() throws Exception {
        driver.get("http://www.google.com");
    }
}
