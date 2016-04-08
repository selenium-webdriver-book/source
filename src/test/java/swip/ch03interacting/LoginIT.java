package swip.ch03interacting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

@RunWith(WebDriverRunner.class)
public class LoginIT {
    @Inject
    private WebDriver driver;

    @Test
    public void completingAForm() throws Exception {
        driver.get("/login.html");

        WebElement email = driver.findElement(By.name("email"));
        email.clear();
        email.sendKeys("john@doe.com");

        WebElement password = driver.findElement(By.name("password"));
        password.clear();
        password.sendKeys("secret");

        driver.findElement(By.cssSelector("input[type='submit']"))
            .click();
    }
}
