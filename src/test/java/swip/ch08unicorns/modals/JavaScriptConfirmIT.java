package swip.ch08unicorns.modals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.junit.Config;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;

@RunWith(SeleniumWebDriverRunner.class)
@Config(exclude = {"browserName=safari", "browserName=htmlunit"})
public class JavaScriptConfirmIT {
    @Inject
    private WebDriver driver;

    @Test
    public void openNewWindow() throws Exception {
        driver.get("/popups.html");

        driver.findElement(By.linkText("Confirm")).click();

        driver.switchTo().alert().accept();

    }
}
