package swip.ch15pageflow.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

interface ExplicitWait {

    Element findElement(Supplier<By> by);

    default Element untilFound(Supplier<By> by) {
        return new FluentWait<>(this)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(100, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class)
                .until((ExplicitWait e) -> findElement(by));
    }
}
