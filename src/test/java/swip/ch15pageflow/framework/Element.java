package swip.ch15pageflow.framework;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;

public class Element extends DelegatingWebElement implements Locatable {

    public Element(WebElement delegate) {
        super(delegate);
    }

    @Override
    public Coordinates getCoordinates() {
        return ((Locatable)delegate).getCoordinates();
    }
}
