package swb.ch02locatingelements;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;

@RunWith(WebDriverRunner.class)
public class ChainedLocatorsIT {

    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/login.html");
    }

    @Test
    public void chainedLocators() throws Exception {

        driver
                .findElement(By.className("forgotten-password"))
                .findElement(By.tagName("a"));
    }

    @Test
    public void chainedStreamLocators() throws Exception {
        driver
                .findElements(By.tagName("a"))
                .stream()
                .filter(e -> e.getText().contains("Forgotten Password"))
                .findFirst()
                .get();
    }


    @Test
    @SuppressWarnings("unused")
    public void chainedFormLocators() throws Exception {

        WebElement loginForm = driver.findElement(By.id("login")); // #1 locate the form itself
        WebElement emailInput = loginForm.findElement(By.name("email")); // #2 narrow down each element
        WebElement passwordInput = loginForm.findElement(By.name("password"));
        WebElement submit = loginForm.findElement(By.className("btn-primary"));

    }
}
