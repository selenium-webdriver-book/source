package swip.ch07managingwebdriver;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(SeleniumWebDriverRunner.class)
@Config(exclude = "browserName=htmlunit")
public class CookieIT {
    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/cookie.html");
    }

    @Test
    public void oneTest() throws Exception {

        assertEquals("", driver.findElement(By.id("cookieValue")).getText());

        driver.findElement(By.name("cookieValue")).sendKeys("new value");
        driver.findElement(By.className("btn")).click();

        assertEquals("new value", driver.findElement(By.id("cookieValue")).getText());
    }

    @Test
    public void anotherTest() throws Exception {

        assertEquals("", driver.findElement(By.id("cookieValue")).getText());

        driver.findElement(By.name("cookieValue")).sendKeys("new value");
        driver.findElement(By.className("btn")).click();

        assertEquals("new value", driver.findElement(By.id("cookieValue")).getText());
    }
}
