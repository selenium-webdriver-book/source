package swip.ch15pageflow.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Element extends DelegatingWebElement implements ExplicitWait, SearchScope {

    public Element(WebElement delegate) {
        super(delegate);
    }

    @Override
    public Element findElement(Supplier<By> by) {
        Element element = new Element(super.findElement(by.get()));
        element.setSearchContext(this);
        element.setLocator((SearchScope e) -> this.untilFound2(by));
        return element;
    }
    public Stream<Element> findElements(Supplier<By> by) {
        return super.findElements(by.get()).stream().map(Element::new);
    }
}
