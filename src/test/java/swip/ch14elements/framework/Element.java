package swip.ch14elements.framework;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;

public class Element extends DelegatingWebElement {

    public Element(WebElement delegate) {
        super(delegate);
    }

}
