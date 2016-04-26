package swip.ch13framework.v1_0_prejava8;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

public class Element implements ExplicitWait {

    private final WebElement webElement;

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }

    @Override
    public WebElement findElement(By by) {
        return webElement.findElement(by);
    }

    public WebElement untilFound(final By by) {  // <2>
        return new FluentWait<>(this)
            .withTimeout(5, TimeUnit.SECONDS)
            .pollingEvery(10, TimeUnit.MILLISECONDS)
            .ignoring(NoSuchElementException.class)
            .until(new Function<Element, WebElement>() {
                @Override
                public WebElement apply(Element element) {
                    return element.findElement(by);
                }
            }); // <3>
    }
}
