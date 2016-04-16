package swip.ch13framework.v1_0_nonjava8;

import com.google.common.base.Function;
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

    public WebElement untilFound(final By by) {  // <2>
        return new FluentWait<>(this)
            .withTimeout(5, TimeUnit.SECONDS)
            .pollingEvery(10, TimeUnit.MILLISECONDS)
            .ignoring(NoSuchElementException.class)
            .until(new Function<Browser, WebElement>() {
                @Override
                public WebElement apply(Browser browser) {
                    return browser.findElement(by);
                }
            }); // <3>
    }
}
