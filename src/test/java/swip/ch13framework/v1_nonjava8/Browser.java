package swip.ch13framework.v1_nonjava8;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import swip.ch12wrapping.v0_5.DelegatingWebDriver;

import java.util.concurrent.TimeUnit;

public class Browser extends DelegatingWebDriver implements ExplicitWait {

    public Browser(WebDriver driver) {
        super(driver);
    }

    public WebElement untilFound(By by) {  // <2>
        return new FluentWait<>(this)
            .withTimeout(10, TimeUnit.SECONDS)
            .pollingEvery(100, TimeUnit.MILLISECONDS)
            .ignoring(NoSuchElementException.class)
            .until((ExplicitWait e) -> findElement(by)); // <3>
    }
}
