package swip.ch07managingwebdriver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/web-driver.xml")
public class InjectedBaseUrlIT {
    @Inject
    private WebDriver driver;

    @Inject
    private String baseUrl; // # URL is injected here

    @Test
    public void loadIndexPage() throws Exception {
        driver.get(baseUrl + "/index.html");
    }
}
