package swb.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;
import javax.inject.Named;
import java.net.URI;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class HttpsIT {
    @Inject
    private WebDriver driver;
    @Inject
    @Named("baseUrlHttps")
    private URI baseUrlHttps;

    @Before
    public void setUp() throws Exception {
        driver.get(baseUrlHttps.toString());
    }

    @Test
    public void canLoadIndexPageViaHttps() throws Exception {
        assertEquals("Selenium WebDriver Book - Index", driver.getTitle());
    }
}
