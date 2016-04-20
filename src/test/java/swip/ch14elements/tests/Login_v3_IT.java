package swip.ch14elements.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import swip.ch14elements.framework.v5.Browser;
import swip.ch14elements.framework.v5.BrowserRunner;

import javax.inject.Inject;

@RunWith(BrowserRunner.class)
public class Login_v3_IT {

    @Inject
    private Browser driver;

    @Test
    public void login() throws Exception {
        driver.get("/login.html");
        driver.setInputText(By.name("email"),"foo@bar.com");
        driver.setInputText(By.name("password"),"secret");
        driver.untilFound(By.cssSelector("input[type='submit']")).click();
    }
}
