package swip.ch13framework.v1_0_prejava8;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class Element implements ExplicitWait {

    private final WebElement webElement;

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }

    @Override
    public WebElement findElement(Supplier<By> by) {
        return webElement.findElement(by.get());
    }

    public WebElement await(final Supplier<By> by) {  // <2>
        return new FluentWait<>((ExplicitWait) this)
            .withTimeout(5, TimeUnit.SECONDS)
            .pollingEvery(10, TimeUnit.MILLISECONDS)
            .ignoring(NoSuchElementException.class)
            .until(new Function<ExplicitWait, WebElement>() {
                @Override
                public WebElement apply(ExplicitWait element) {
                    return element.findElement(by);
                }
            }); // <3>
    }
}
