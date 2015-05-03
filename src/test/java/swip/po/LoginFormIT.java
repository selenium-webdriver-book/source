package swip.po;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.junit.Config;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(SeleniumWebDriverRunner.class)
@Config(exclude = {"browserName=safari"})
public class LoginFormIT {
    @Inject
    private WebDriver driver;

    @Test
    public void checkLoginForm() throws Exception {
        driver.get("/login.html");

        LoginForm loginForm = new LoginForm(driver.findElement(By.id("login")));
        loginForm.loginAs("foo@bar.com", "secret");

        assertEquals("You Are Logged In", driver.getTitle());
    }
}
