package swip.ch15pageflow.v2.framework;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public interface ExplicitWait extends SearchScope {

     Log log  = LogFactory.getLog(ExplicitWait.class);

    default Element untilFound(Supplier<By> by) {
        Element element = null;
        try {
            element = new FluentWait<>(this)
                .withTimeout(10, SECONDS)
                .pollingEvery(5, MILLISECONDS)
                .ignoring(Exception.class)
                .until((ExplicitWait e) -> e.findElement(by));
            element.setSearchContext(this);
            element.setLocator((SearchScope e) -> this.untilFound(by));
        } catch (Exception e) {
             log.info(this.getText());
        }

        return element;
    }

    String getText();

    default void until(Predicate<ExplicitWait> predicate) {
        FluentWait<ExplicitWait> fluentWait = new FluentWait<>(this)
            .withTimeout(10, SECONDS)
            .pollingEvery(100, MILLISECONDS)
            .ignoring(NoSuchElementException.class);
        fluentWait.until(
            (ExplicitWait where) -> predicate.test(where)
        );
    }

}
