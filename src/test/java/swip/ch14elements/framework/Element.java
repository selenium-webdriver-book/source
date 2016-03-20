package swip.ch14elements.framework;

import org.openqa.selenium.WebElement;

public class Element extends DelegatingWebElement  {

    public Element(WebElement delegate) {
        super(delegate);
    }

}
