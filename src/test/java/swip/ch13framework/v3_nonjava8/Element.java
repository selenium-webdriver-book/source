package swip.ch13framework.v3_nonjava8;

import org.openqa.selenium.WebElement;

public class Element extends DelegatingWebElement {

    public Element(WebElement delegate) {
        super(delegate);
    }
}
