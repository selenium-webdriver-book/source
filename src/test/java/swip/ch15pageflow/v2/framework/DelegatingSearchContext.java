package swip.ch15pageflow.v2.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class DelegatingSearchContext<T extends SearchContext>  //<1>
    implements SearchContext, ExplicitWait {
    protected T delegate; // <2>

    public DelegatingSearchContext(T delegate) {
        this.delegate = delegate;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return delegate.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return delegate.findElement(by);
    }

    @Override
    public Element findElement(Supplier<By> by) {
        Element element = new Element(findElement(by.get()));
        element.setSearchContext(this);
        element.setLocator((SearchScope e) -> this.untilFound(by));
        return element;
    }

    public Stream<Element> findElements(Supplier<By> by) {
        return findElements(by.get()).stream().map(Element::new);
    }

    public String getText(Supplier<By> by) {
        return untilFound(by).getText();
    }

    public void click(Supplier<By> by) {
        untilFound(by).click();
    }

}
