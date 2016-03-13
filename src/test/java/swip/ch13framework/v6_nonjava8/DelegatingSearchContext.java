package swip.ch13framework.v6_nonjava8;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DelegatingSearchContext<T extends SearchContext>  //<1>
    implements SearchContext, ExplicitWait {
    protected final T delegate; // <2>

    public DelegatingSearchContext(T delegate) {
        this.delegate = delegate;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return delegate.findElements(by);
    }

    @Override
    public Element findElement(By by) {
        return new Element(delegate.findElement(by));
    }

    @Override
    public Optional<Element> optionalElement(By by) {
        try {
            return Optional.of(findElement(by));
        } catch (NoSuchElementException ignored) {
            return Optional.absent();
        }
    }

    @Override
    public Element untilFound(By by) {
        return new FluentWait<>(this)
            .withTimeout(10, TimeUnit.SECONDS)
            .pollingEvery(100, TimeUnit.MILLISECONDS)
            .ignoring(NoSuchElementException.class)
            .until(
                new Function<ExplicitWait, Element>() {
                    @Override
                    public Element apply(ExplicitWait e) {
                        return findElement(by);
                    }
                }
            ); // <3>
    }
}
