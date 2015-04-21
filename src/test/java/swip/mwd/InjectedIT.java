package swip.mwd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/web-driver.xml")
public class InjectedIT {
    @Inject
    private WebDriver driver;

    @Test
    public void loadIndexPage() throws Exception {
        driver.get("/index.html");
    }
}
