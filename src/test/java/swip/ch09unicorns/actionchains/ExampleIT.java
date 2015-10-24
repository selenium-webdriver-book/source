package swip.ch09unicorns.actionchains;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class ExampleIT {
    @Inject
    private WebDriver driver;

    @Test
    public void actionChainExampleElement() throws Exception {
        driver.get("/login.html");

        new Actions(driver)
                .sendKeys(driver.findElement(By.name("email")), "john.doe@email.com")
                .sendKeys(driver.findElement(By.name("password")), "secret")
                .click(driver.findElement(By.cssSelector("input[type='submit']")))
                .perform();

        assertEquals("You Are Logged In",
                driver.findElement(By.tagName("h1")).getText());
    }

    @Test
    public void actionChainExample() throws Exception {
        driver.get("/login.html");

        new Actions(driver)
                .moveToElement(driver.findElement(By.name("email")))
                .click()
                .sendKeys("john.doe@email.com")
                .moveToElement(driver.findElement(By.name("password")))
                .click()
                .sendKeys("secret")
                .moveToElement(driver.findElement(By.cssSelector("input[type='submit']")))
                .click()
                .perform();

        assertEquals("You Are Logged In",
                driver.findElement(By.tagName("h1")).getText());
    }

}
