package swip.ch14elements.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch13framework.v6.Browser;
import swip.ch13framework.v6.Element;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

@RunWith(WebDriverRunner.class)
public class LoginV2IT {

    private Browser driver;

    @Inject public void setDriver(WebDriver webDriver) {
        this.driver = new Browser(webDriver);
    }

    @Test public void login() throws Exception {
        driver.get("/login.html");

        Element email = driver.untilFound(By.name("email"));
        email.clear();
        email.sendKeys("john@doe.com");

        Element password = driver.untilFound(By.name("password"));
        password.clear();
        password.sendKeys("secret");

        driver.untilFound(By.cssSelector("input[type='submit']"))
            .click();
    }
}
