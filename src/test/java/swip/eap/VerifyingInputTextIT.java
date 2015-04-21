package swip.eap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SeleniumWebDriverRunner.class)
public class VerifyingInputTextIT {
    @Inject
    private WebDriver driver;

    @Test
    public void inputShouldHaveInputText() throws Exception {
        driver.get("http://localhost:8080/styled-elements.html");

        assertThat(driver.findElement(By.name("text")).getAttribute("value"), equalTo("Input text"));
    }
}
