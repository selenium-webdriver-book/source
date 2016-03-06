package swip.ch15pageflow.v2.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.concurrent.TimeUnit.SECONDS;

public interface ExplicitWait extends SearchScope{

    default void click(Supplier<By> by) {
         untilFound(by).click();
    }

    default Element untilFound(Supplier<By> by) {
        Element element = new FluentWait<>(this)
            .withTimeout(10, TimeUnit.SECONDS)
            .pollingEvery(5, TimeUnit.MILLISECONDS)
            .ignoring(Exception.class)
            .until((ExplicitWait e) -> e.findElement(by));
        element.setSearchContext(this);
        element.setLocator((SearchScope e) -> this.untilFound(by));
        return element;
    }

    default void until(Predicate<ExplicitWait> predicate)  {
        FluentWait<ExplicitWait> fluentWait = new FluentWait<>(this)
            .withTimeout(10, SECONDS)
            .pollingEvery(100, TimeUnit.MILLISECONDS)
            .ignoring(NoSuchElementException.class);
        fluentWait.until(
            (ExplicitWait where) -> predicate.test(where)
        );

    }

}
