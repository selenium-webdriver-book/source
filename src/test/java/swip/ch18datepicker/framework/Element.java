package swip.ch18datepicker.framework;

import org.openqa.selenium.WebElement;

public class Element extends DelegatingWebElement {

    public Element(WebElement delegate) {
        super(delegate);
    }

}
