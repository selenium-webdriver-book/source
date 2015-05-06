package swip.ch08unicorns.modals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.HasInputDevices;
import swip.junit.Config;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(SeleniumWebDriverRunner.class)
@Config(exclude = {"browserName=safari", "browserName=htmlunit", "browserName=chrome"})
public class HttpAuthenticationIT<W extends WebDriver & HasInputDevices> {
    @Inject
    private W driver;

    @Test
    public void authViaUrl() throws Exception {
        String username = "foo@bar.com";
        String password = "secret";
        driver.get("http://" + username + ":" + password + "@localhost:8080/auth.html");

        assertEquals("Authentication", driver.findElement(By.tagName("h1")).getText());

    }
}
