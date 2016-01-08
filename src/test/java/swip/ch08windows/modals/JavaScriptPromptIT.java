package swip.ch08windows.modals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class JavaScriptPromptIT {
    @Inject
    private WebDriver driver;

    @Test
    public void openNewWindow() throws Exception {
        driver.get("/popups.html");

        driver.findElement(By.linkText("Prompt")).click();

        new WebDriverWait(driver, 1)
                .until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        alert.sendKeys("text");
        assertEquals("Prompt?", alert.getText());
        alert.dismiss();

    }
}
