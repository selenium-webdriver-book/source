package swip.ch15pageflow.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public interface ExplicitWait {

    Element findElement(Supplier<By> by);

    default Element untilFound(Supplier<By> by) {
        return new FluentWait<>(this)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.MILLISECONDS)
                .ignoring(Exception.class)
                .until((ExplicitWait e) -> e.findElement(by));
    }

    default Element untilFound2(Supplier<By> by) {
        Element element = new FluentWait<>(this)
            .withTimeout(10, TimeUnit.SECONDS)
            .pollingEvery(5, TimeUnit.MILLISECONDS)
            .ignoring(Exception.class)
            .until((ExplicitWait e) -> e.findElement(by));
        element.setSerachContext(this);
        element.setBy((ExplicitWait e) -> this.untilFound2(by));
        return element;
    }

}
