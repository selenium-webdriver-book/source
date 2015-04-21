package swip.mwd;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ShutdownHookWebDriverIT {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
    }

    @Test
    public void checkTheRegistrationPage() throws Exception {
        // get pages, interact and then verify
    }
}
