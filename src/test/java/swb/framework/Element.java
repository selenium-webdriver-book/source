package swb.framework;

import org.openqa.selenium.WebElement;

public class Element extends DelegatingWebElement implements FormElements {

    public Element(WebElement delegate) {
        super(delegate);
    }

    @Override
    public String toString() {
        String tagName = delegate.getTagName();
        return "[Element: " + (tagName.equals("input") ?
            delegate.getAttribute("value") : tagName.equals("img") ?
            delegate.getAttribute("src") : delegate.getText()) + "] wrapping " + delegate;
    }
}
