package swip.ch13framework.v1;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.function.Supplier;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public interface ExplicitWait {

    WebElement findElement(Supplier<By> by); // <1>

    default WebElement untilFound(Supplier<By> by) {  // <2>
        return new FluentWait<>(this)
            .withTimeout(1, SECONDS)
            .pollingEvery(10, MILLISECONDS)
            .ignoring(NoSuchElementException.class)
            .until((ExplicitWait e) -> findElement(by)); // <3>
    }
}