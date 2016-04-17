package swip.ch13framework.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public interface ExplicitWait extends SearchScope {

    default Element untilFound(By by) {  // <2>
        return new FluentWait<>(this)
                .withTimeout(5, SECONDS)
                .pollingEvery(100, MILLISECONDS)
                .ignoring(NoSuchElementException.class)
                .until((ExplicitWait e) -> findElement(by)); // <3>
    }
}
