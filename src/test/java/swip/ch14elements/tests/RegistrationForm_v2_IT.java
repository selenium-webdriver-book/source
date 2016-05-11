package swip.ch14elements.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swip.ch13framework.v4.Browser;
import swip.ch13framework.v4.Element;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static swip.locators.CssSelector.SUBMIT;
import static swip.locators.Name.EMAIL;
import static swip.locators.Name.PASSWORD;

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
