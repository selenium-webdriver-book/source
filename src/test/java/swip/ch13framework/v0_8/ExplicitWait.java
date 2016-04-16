package swip.ch13framework.v0_8;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public abstract class ExplicitWait {

    public abstract WebElement findElement(By by); // <1>

    public WebElement untilFound(By by) {  // <2>
        return new FluentWait<>(this)
            .withTimeout(10, SECONDS)
            .pollingEvery(100, MILLISECONDS)
            .ignoring(NoSuchElementException.class)
            .until((ExplicitWait e) -> findElement(by)); // <3>
    }
}
