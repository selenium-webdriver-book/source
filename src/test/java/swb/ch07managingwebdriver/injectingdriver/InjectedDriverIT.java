package swb.ch07managingwebdriver.injectingdriver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebDriverConfig.class)
public class InjectedDriverIT {
    @Inject
    private WebDriver driver;

    @Test
    public void loadIndexPage() throws Exception {
        driver.get("http://localhost:8080/index.html");
    }
}
