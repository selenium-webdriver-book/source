package swip.eap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;
import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SeleniumWebDriverRunner.class)
public class VerifyingInputTextIT {
    @Inject
    private WebDriver driver;
    @Inject
    private URI baseUrl;

    @Test
    public void inputShouldHaveInputText() throws Exception {
        driver.get(baseUrl + "/styled-elements.html");

        assertThat(driver.findElement(By.name("text")).getAttribute("value"), equalTo("Input text"));
    }
}
