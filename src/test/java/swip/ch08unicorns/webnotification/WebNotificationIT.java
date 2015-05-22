package swip.ch08unicorns.webnotification;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import swip.ch07managingwebdriver.Config;
import swip.ch07managingwebdriver.SeleniumWebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(SeleniumWebDriverRunner.class)
@Config(exclude = "browserName=htmlunit")
public class WebNotificationIT<W extends WebDriver & JavascriptExecutor> {
    @Inject
    private W driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/web-notification.html");
    }

    @Test
    public void webNotification() throws Exception {

        Notification notification = new Notification(driver);

        driver.findElement(By.linkText("Show A Web Notification")).click();

        new WebDriverWait(driver, 3).until(notification.isDisplayed());

        assertEquals("A Web Notification", notification.getTitle());
    }
}
