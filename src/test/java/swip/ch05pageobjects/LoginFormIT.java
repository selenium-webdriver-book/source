package swip.ch05pageobjects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import swip.framework.Config;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

@RunWith(WebDriverRunner.class)
@Config(exclude = {"browserName=htmlunit", "browserName=safari"})
public class LoginFormIT {
    @Inject
    private WebDriver driver;

    @Test
    public void checkLoginForm() throws Exception {
        driver.get("/login.html");

        LoginForm loginForm = new LoginForm(driver.findElement(By.id("login")));
        loginForm.loginAs("foo@bar.com", "secret");

        new WebDriverWait(driver, 1)
                .until(ExpectedConditions.titleIs("You Are Logged In"));
    }
}
