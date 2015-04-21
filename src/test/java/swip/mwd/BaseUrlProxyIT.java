package swip.mwd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class) // # tell JUnit to run the test with a custom runner: the Spring runner
@ContextConfiguration(locations = "/web-driver.xml") // # tell Spring where the configuration file is on the classpath
public class BaseUrlProxyIT {
    @Inject // # indicate to Spring where to inject a driver
    private WebDriver driver;

    @Test
    public void useDriver() throws Exception {
        assertNotNull(driver);
        // open pages, interact with them, verify the result
    }
}
