package swip.ch13framework.tests;

import com.google.common.base.Function;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import swip.tests.TestTimer;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.By.linkText;

@RunWith(WebDriverRunner.class)
public class LocatingLogicWithExplicitWaitIT extends TestTimer {

    @Inject
    private WebDriver driver;

    @Test
    public void usingExplicitWait() {
        driver.get("/location-chooser.html");
        driver.findElement(linkText("choose location")).click();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);  //<1>

        WebElement location = webDriverWait.until(
            new Function<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(By.id("location"));
                }
            }
        );

        FluentWait<WebElement> webElementWait              //<2>
            = new FluentWait<>(location)
            .withTimeout(5000, MILLISECONDS)
            .ignoring(NoSuchElementException.class);
        WebElement canada = webElementWait.until(
            new Function<WebElement, WebElement>() {
                @Override
                public WebElement apply(WebElement element) {
                    return location.findElement(linkText("MEXICO"));
                }
            }
        );
        canada.click();
        WebElement allCanada = webElementWait.until(
            new Function<WebElement, WebElement>() {
                @Override
                public WebElement apply(WebElement element) {
                    return location.findElement(linkText("Cancun"));
                }
            }
        );
        allCanada.click();
        assertEquals(0, driver.findElements(linkText("Cancun")).size());
        assertEquals("Cancun", driver
            .findElement(By.cssSelector(".tools-location strong"))
            .getText());
    }

    @Test
    public void usingExplicitWaitLambda() {
        driver.get("/location-chooser.html");
        driver.findElement(linkText("choose location")).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5); // <1>

        WebElement tabMenu = webDriverWait
            .until((WebDriver d) -> driver.findElement(By.id("location")));

        FluentWait<WebElement> webElementWait = new FluentWait<>(tabMenu) // <2>
            .withTimeout(5000, MILLISECONDS)
            .pollingEvery(100, MILLISECONDS)
            .ignoring(Exception.class);

        webElementWait.until(
            (WebElement element) -> tabMenu.findElement(linkText("MEXICO")))
            .click();
        webElementWait
            .until((WebElement element) -> tabMenu.findElement(linkText("Cancun")))
            .click();
        assertEquals(0, tabMenu.findElements(linkText("Cancun")).size());
        assertEquals("Cancun", driver
            .findElement(By.cssSelector(".tools-location strong"))
            .getText());
    }
}
