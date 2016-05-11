package swip.ch13framework.v1_0_prejava8;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import swip.ch12wrapping.v0_8.DelegatingWebDriver;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class Browser extends DelegatingWebDriver implements ExplicitWait {

    public Browser(WebDriver driver) {
        super(driver);
    }

    public WebElement await(final Supplier<By> by) {  // <2>
        return new FluentWait<>((ExplicitWait) this)
            .withTimeout(5, TimeUnit.SECONDS)
            .pollingEvery(10, TimeUnit.MILLISECONDS)
            .ignoring(NoSuchElementException.class)
            .until(new Function<ExplicitWait, WebElement>() {
                @Override
                public WebElement apply(ExplicitWait browser) {
                    return browser.findElement(by);
                }
            }); // <3>
    }
}
