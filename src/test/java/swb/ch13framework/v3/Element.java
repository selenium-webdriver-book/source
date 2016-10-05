package swb.ch13framework.v3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

public class Element extends DelegatingWebElement implements ExplicitWait {

    public Element(WebElement delegate) {
        super(delegate);
    }

    @Override
    public Element findElement(Supplier<By> by) {
        return new Element(delegate.findElement(by.get()));
    }
}
