package swip.ch08unicorns.modals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import swip.ch07managingwebdriver.injecting.Config;
import swip.ch07managingwebdriver.injecting.WebDriverRunner;

import javax.inject.Inject;

@RunWith(WebDriverRunner.class)
@Config(exclude = {"browserName=safari", "browserName=htmlunit", "browserName=phantomjs"})
public class JavaScriptAlertIT {
    @Inject
    private WebDriver driver;

    @Test
    public void openNewWindow() throws Exception {
        driver.get("/popups.html");

        driver.findElement(By.linkText("Alert")).click();

        new WebDriverWait(driver, 2).until(ExpectedConditions.alertIsPresent());

        driver.switchTo().alert().accept();

    }
}
