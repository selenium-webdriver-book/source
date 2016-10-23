package swb.ch09unicorns.actionchains;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class CopyAndPasteIT {
    @Inject
    private WebDriver driver;

    @Test
    @Ignore("does not work in Chrome")
    public void copyAndPaste() throws Exception {

        driver.get("/registration-form.html");

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) this.driver;
        String userAgent = (String) javascriptExecutor.executeScript("return navigator.userAgent;");
        Keys modifier = userAgent.contains("Mac OS X") ? Keys.COMMAND : Keys.CONTROL;

        WebElement email = this.driver.findElement(By.name("email"));
        WebElement password = this.driver.findElement(By.name("password"));

        new Actions(this.driver)
                .sendKeys(email, "john.doe@email.com")
                .sendKeys(email, Keys.chord(modifier, "a", "c"))
                .sendKeys(password, Keys.chord(modifier, "v"))
                .perform();

        assertEquals("john.doe@email.com",
                password.getAttribute("value"));
    }
}
