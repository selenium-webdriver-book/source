package swip.ch06pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginForm {
    private final WebElement loginForm;

    public LoginForm(WebElement loginForm) {
        this.loginForm = loginForm;
    }

    public void loginAs(String email, String password) {
        loginForm.findElement(By.cssSelector("input[name='email']"))
                .sendKeys(email);
        loginForm.findElement(By.cssSelector("input[name='password']"))
                .sendKeys(password);
        loginForm.findElement(By.cssSelector("input[type='submit']"))
                .click();
    }
}
