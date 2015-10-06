package swip.ch07managingwebdriver.injectingdriver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;
import java.net.URI;

@RunWith(WebDriverRunner.class)
public class SpringConfigIT {
    @Inject
    private WebDriver driver;

    @Inject
    private URI baseUrl;

    @Test
    public void loadIndexPage() throws Exception {
        driver.get(baseUrl + "/index.html");
    }
}
