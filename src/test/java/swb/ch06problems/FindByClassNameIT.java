package swb.ch06problems;

import com.google.common.base.Function;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import swb.framework.WebDriverRunner;
import swb.tests.TestTimer;

import javax.inject.Inject;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

@RunWith(WebDriverRunner.class)
public class FindByClassNameIT extends TestTimer {

    @Inject
    private WebDriver driver;

    @Before
    public void setup() {
        driver.get("/react-datepicker.html");
    }

    @Test(expected = NoSuchElementException.class)
    public void failedToLocate() {
        driver.findElement(By.className("ignore-react-onclickoutside"));
    }

    @Test(expected = TimeoutException.class)
    public void failedToLocateWithExplicitWaut() {
        WebDriverWait wait = new WebDriverWait(driver, 1, 10);
        wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(
                    By.className("ignore-react-onclickoutside"));
            }
        }).click();
    }

    @Test(expected = TimeoutException.class)
    public void failedToLocateAgain() {
        WebElement webElement = driver.findElement(
            By.className("react-datepicker__input-container"));

        FluentWait<WebElement> webElementFluentWait = new FluentWait<>(webElement)
            .withTimeout(1, SECONDS)
            .pollingEvery(10, MILLISECONDS)
            .ignoring(NoSuchElementException.class);
        webElementFluentWait.until(new Function<WebElement, WebElement>() {
            @Override
            public WebElement apply(WebElement webElement) {
                return webElement.findElement(
                    By.className("ignore-react-onclickoutside"));
            }
        }).click();
    }
}
