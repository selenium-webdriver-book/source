package swip.ch13framework.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swip.framework.TestTimer;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.By.linkText;

@RunWith(WebDriverRunner.class)
public class LocatingLogicWithImplicitWaitIT extends TestTimer{

    @Inject private WebDriver driver;

    @Test public void usingImplicitWait() {
        driver.manage().timeouts().implicitlyWait(5, SECONDS); // <1>
        driver.get("/location-chooser.html");
        driver.findElement(linkText("change location")).click();
        WebElement tabMenu = driver.findElement(By.id("location"));
        tabMenu.findElement(linkText("CANADA")).click();
        tabMenu.findElement(linkText("Ontario")).click();
        assertEquals(0, tabMenu.findElements(linkText("Ontario")).size());
        assertEquals("Ontario", driver
            .findElement(By.cssSelector(".tools-location strong"))
            .getText());
    }

    @Test(expected = NoSuchElementException.class)
    public void usingImplicitWaitButNotFound() {
        driver.manage().timeouts().implicitlyWait(5, SECONDS); // <1>
        driver.get("/location-chooser.html");
        driver.findElement(linkText("change location")).click();
        WebElement tabMenu = driver.findElement(By.id("location"));
        tabMenu.findElement(linkText("CANADA")).click();
        tabMenu.findElement(linkText("Ontario")).click();
        assertEquals(0, tabMenu.findElements(linkText("Ontario")).size());
        assertEquals("Ontario", driver
            .findElement(By.cssSelector(".tools-locati"))
            .getText());
    }

}
