package swip.mwd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.HasInputDevices;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/web-driver.xml")
public class InjectedGenericIT<W extends WebDriver & HasInputDevices & JavascriptExecutor> {
    @Inject
    private W driver;

    @Test
    public void useDriver() throws Exception {
        assertNotNull(driver);
        // open pages, interact with them, verify the result
    }
}
