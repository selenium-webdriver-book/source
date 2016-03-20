package swip.ch15pageflow.v1.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

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
    public Element findElement(Supplier<By> by) {
        return new Element(findElement(by.get()));
    }

    public Stream<Element> findElements(Supplier<By> by) {
        return findElements(by.get()).stream().map(Element::new);
    }

}
