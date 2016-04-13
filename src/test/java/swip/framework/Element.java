package swip.framework;

import org.openqa.selenium.WebElement;

public class Element extends DelegatingWebElement implements FormElements {

    public Element(WebElement delegate) {
        super(delegate);
    }
}
