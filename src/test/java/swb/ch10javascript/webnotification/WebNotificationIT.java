package swb.ch10javascript.webnotification;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class WebNotificationIT {
    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/web-notification.html");
    }

    @Test
    public void webNotification() throws Exception {

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Notification notification = new Notification(jsExecutor);

        driver.findElement(By.linkText("Show A Web Notification")).click();

        assertEquals("A Web Notification", notification.getTitle());
    }
}
