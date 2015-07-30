package swip.ch05pageobjects.fluentbuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch07managingwebdriver.Config;
import swip.ch07managingwebdriver.SeleniumWebDriverRunner;

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

        new LoginForm(driver.findElement(By.id("login")))
                .username("foo@bar.com")
                .password("secret")
                .submit();

        assertEquals("You Are Logged In", driver.getTitle());
    }
}
