package swip.ch10javascript.webnotification;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import swip.framework.Config;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
@Config(exclude = "browserName=htmlunit")
public class WebNotificationIT {
    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/web-notification.html");
    }

    @Test
    public void webNotification() throws Exception {

        Notification notification = new Notification((JavascriptExecutor) driver);

        driver.findElement(By.linkText("Show A Web Notification")).click();

        assertEquals("A Web Notification", notification.getTitle());
    }
}
