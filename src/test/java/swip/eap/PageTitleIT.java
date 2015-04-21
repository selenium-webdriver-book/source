package swip.eap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

@RunWith(SeleniumWebDriverRunner.class)
public class PageTitleIT {
    @Inject
    private WebDriver driver;

    @Test
    public void checkThePageTitle() throws Exception {
        driver.get("http://localhost:8080/styled-elements.html");

        assertThat(driver.getTitle(), containsString("Styled Elements"));
    }
}
