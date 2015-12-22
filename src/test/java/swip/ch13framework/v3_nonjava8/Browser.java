package swip.ch13framework.v3_nonjava8;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

public class Browser implements ExplicitWait {
    private final WebDriver driver;

    public Browser(WebDriver driver) {
        this.driver = driver;
    }

    public void get(String url) {
        driver.get(url);
    }

    public Element findElement(By by) {
        return new Element(driver.findElement(by));
    }

    public Element untilFound(By by) {  // <2>
        return new FluentWait<>(this)
            .withTimeout(10, TimeUnit.SECONDS)
            .pollingEvery(100, TimeUnit.MILLISECONDS)
            .ignoring(NoSuchElementException.class)
            .until((ExplicitWait e) -> findElement(by)); // <3>
    }
}
