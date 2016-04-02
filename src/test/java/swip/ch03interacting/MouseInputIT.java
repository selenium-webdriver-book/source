package swip.ch03interacting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

@RunWith(WebDriverRunner.class)
public class MouseInputIT {

    @Inject
    private WebDriver driver;

    @Test
    public void completeAFormUsingBothMouseAndKeyboard() throws Exception {
        driver.get("/mailing-list.html");
        driver
                .findElement(By.name("email"))
                .sendKeys("john.doe@swip.com");

        driver
                .findElement(By.name("terms"))
                .click(); // #A click the T&Cs checkbox

        WebElement submitButton = driver.findElement(By.tagName("button")); // #B find the double submit button

        new Actions(driver) // #C create actions from the driver
                .doubleClick(submitButton) // #D add a double-click to the sequence
                .perform(); // #E perform the sequence
    }
}
