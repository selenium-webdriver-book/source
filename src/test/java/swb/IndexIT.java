package swb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class IndexIT {

    @Inject
    private WebDriver driver;

    @Test
    public void indexPageTitle() throws Exception {
        driver.get("");

        assertEquals("Selenium WebDriver Book - Index", driver.getTitle());
    }
}
