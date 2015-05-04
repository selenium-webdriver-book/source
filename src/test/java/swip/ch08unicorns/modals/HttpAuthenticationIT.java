package swip.ch08unicorns.modals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.HasInputDevices;
import swip.junit.Config;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(SeleniumWebDriverRunner.class)
@Config(exclude = {"browserName=safari", "browserName=htmlunit", "browserName=chrome"})
public class HttpAuthenticationIT<W extends WebDriver & HasInputDevices> {
    @Inject
    private W driver;

    @Test
    public void openNewWindow() throws Exception {
        new Thread(() -> driver.get("/auth.html")).start();

        //new WebDriverWait(driver,20).until(ExpectedConditions.alertIsPresent());

        Thread.sleep(1000);

        driver.getKeyboard().sendKeys("foo");


        assertEquals("Authentication", driver.findElement(By.tagName("h1")).getText());

    }
}
