package swip.ch06problems;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

@RunWith(WebDriverRunner.class)
public class CommonExceptionsIT {
    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/webdriver-exceptions.html");
    }

    @Test(expected = ElementNotVisibleException.class)
    public void invisibleElementShouldNotBeVisible() throws Exception {
        driver.findElement(By.id("invisible")).click();
    }

    @Test(expected = NoSuchElementException.class)
    public void noSuchElement() throws Exception {
        driver.findElement(By.id("no-such-element!")).click();
    }

    @Test(expected = StaleElementReferenceException.class)
    public void elementShouldBeStaleWhenPageChanges() throws Exception {
        WebElement button = driver.findElement(By.id("button"));
        driver.get("/");
        button.click();
    }
}
