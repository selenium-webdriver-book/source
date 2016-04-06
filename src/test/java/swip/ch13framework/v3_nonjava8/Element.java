package swip.ch13framework.v3_nonjava8;

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

    public void click() {
        webElement.click();
    }


    @Override
    public Element findElement(By by) {
        return new Element(webElement.findElement(by));
    }

    public String getText() {
        return webElement.getText();
    }

    public Element untilFound(By by) {  // <2>
        return new FluentWait<>(this)
            .withTimeout(10, TimeUnit.SECONDS)
            .pollingEvery(100, TimeUnit.MILLISECONDS)
            .ignoring(NoSuchElementException.class)
            .until(new Function<Element, Element>() {
                @Override
                public Element apply(Element element) {
                    return element.findElement(by);
                }
            }); // <3>
    }
}
