package swip.ch08unicorns.cookie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import swip.ch07managingwebdriver.injecting.Config;
import swip.ch07managingwebdriver.injecting.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
@Config(exclude = {"browserName=htmlunit", "browserName=phantomjs"})
public class CookieIT {
    public static final String UNDEFINED = "";
    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/cookie.html");
    }

    @Test
    public void oneTest() throws Exception {

        assertEquals(UNDEFINED, getCookieValue());

        driver.findElement(By.name("cookieValue")).sendKeys("new value");
        driver.findElement(By.className("btn")).click();

        assertEquals("new value", getCookieValue());
    }

    @Test
    public void anotherTest() throws Exception {

        assertEquals(UNDEFINED, getCookieValue());

        driver.findElement(By.name("cookieValue")).sendKeys("new value");
        driver.findElement(By.className("btn")).click();

        assertEquals("new value", getCookieValue());
    }

    private String getCookieValue() {
        return new WebDriverWait(driver, 1)
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .until((WebDriver driver) -> driver.findElement(By.id("cookieValue")))
                .getText();
    }
}
