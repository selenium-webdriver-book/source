package swip.ch14elements.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import swip.ch13framework.v4.SearchScope;
import swip.ch14elements.v5.Browser;
import swip.ch14elements.v5.BrowserRunner;

import javax.inject.Inject;

@RunWith(BrowserRunner.class)
public class RegistrationForm_v4_IT {

    @Inject
    private Browser driver;

    @Test
    public void register() throws Exception {
        driver.get("/registration-form-new.html");

        driver.setInputText(By.name("email"), "john@doe.com");
        driver.setInputText(By.name("password"), "secret");
        driver.selectByVisibleText(By.name("hearAbout"), "Friend");
        driver.setRadio(By.name("contact"), "email");
        driver.selectByVisibleText(By.name("interest"), "Movies", "Music");
        driver.setInputText(By.name("tellus"), "---");
        driver.setCheckboxValue(By.name("terms"), true);
        driver.click(By.tagName("button"));

        driver.until((SearchScope d) -> driver.getTitle().contains("Thank You"));
    }
}
