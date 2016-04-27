package swip.ch13framework.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import swip.ch05pageobjects.LoginForm;
import swip.ch13framework.v4.Browser;
import swip.ch13framework.v4.BrowserRunner;
import swip.ch13framework.v4.Element;

import javax.inject.Inject;

import static swip.locators.Id.LOGIN;

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
        Element login = driver.untilFound(LOGIN);

        LoginForm loginForm = new LoginForm(login);
        loginForm.loginAs("foo@bar.com", "secret");

        new WebDriverWait(driver, 1)
            .until(ExpectedConditions.titleIs("You Are Logged In"));
    }
}
