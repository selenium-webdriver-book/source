package swip.ch15pageflow.v1.framework;

import org.openqa.selenium.WebElement;

public class Element extends DelegatingWebElement implements ExplicitWait, SearchScope {

    public Element(WebElement delegate) {
        super(delegate);
    }

}
