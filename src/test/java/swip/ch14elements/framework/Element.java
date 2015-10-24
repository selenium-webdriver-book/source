package swip.ch14elements.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Element extends DelegatingWebElement implements ExplicitWait, SearchScope {

    public Element(WebElement delegate) {
        super(delegate);
    }

    @Override
    public Element findElement(By by) {
        return new Element(super.findElement(by));
    }
}
