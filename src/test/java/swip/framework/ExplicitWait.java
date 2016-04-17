package swip.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public interface ExplicitWait extends SearchScope {

    default Element untilFound(Supplier<By> by) {
        return new FluentWait<>(this)
            .withTimeout(10, SECONDS)
            .pollingEvery(5, MILLISECONDS)
            .ignoring(Exception.class)
            .until((ExplicitWait e) -> e.findElement(by));
    }

    default void until(Predicate<SearchScope> predicate) {
        new FluentWait<>(this)
            .withTimeout(10, SECONDS)
            .pollingEvery(100, MILLISECONDS)
            .ignoring(NoSuchElementException.class)
            .until(
                (SearchScope where) -> predicate.test(where)
            );
    }
}
