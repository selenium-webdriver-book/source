package swb.ch13framework.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import swb.ch05pageobjects.LoginForm;
import swb.ch13framework.v4.Browser;
import swb.ch13framework.v4.BrowserRunner;
import swb.ch13framework.v4.Element;

import javax.inject.Inject;

import static swb.locators.Id.LOGIN;

@RunWith(BrowserRunner.class)
public class LoginForm_v2_IT {
    @Inject
    private Browser driver;

    @Before
    public void gotoLoginPage() {
        driver.get("/login.html");
    }

    @Test
    public void checkLoginForm() throws Exception {
        Element login = driver.await(LOGIN);

        LoginForm loginForm = new LoginForm(login);
        loginForm.loginAs("foo@bar.com", "secret");

        new WebDriverWait(driver, 1)
            .until(ExpectedConditions.titleIs("You Are Logged In"));
    }
}
