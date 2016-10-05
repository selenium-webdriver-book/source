package swb.ch14elements.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swb.ch13framework.v4.Browser;
import swb.ch13framework.v4.Element;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;

import static swb.locators.CssSelector.SUBMIT;
import static swb.locators.Name.EMAIL;
import static swb.locators.Name.PASSWORD;

@RunWith(WebDriverRunner.class)
public class RegistrationForm_v2_IT {

    private Browser driver;

    @Inject
    public void setDriver(WebDriver webDriver) {
        this.driver = new Browser(webDriver);
    }

    @Test
    public void register() throws Exception {
        driver.get("/registration-form.html");

        Element email = driver.await(EMAIL);
        email.clear();
        email.sendKeys("john@doe.com");

        Element password = driver.await(PASSWORD);
        password.clear();
        password.sendKeys("secret");

        driver.click(SUBMIT);
    }
}
