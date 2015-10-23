package swip.ch08unicorns.actionchains;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class CopyAndPasteIT {
    @Inject
    private WebDriver driver;

    @Test
    public void copyAndPaste() throws Exception {

        driver.get("/registration-form.html");

        String osName = System.getProperty("os.name");
        Keys modifier = osName.equals("Mac OS X") ? Keys.COMMAND : Keys.CONTROL;

        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));

        new Actions(driver)
                .sendKeys(email, "john.doe@email.com")
                .sendKeys(email, Keys.chord(modifier, "a", "c"))
                .sendKeys(password, Keys.chord(modifier, "v"))
                .perform();

        assertEquals("john.doe@email.com",
                password.getAttribute("value"));
    }
}
