package swip.ch13framework.v2;

import org.openqa.selenium.WebElement;

public class Element extends DelegatingWebElement implements ExplicitWait {

    public Element(WebElement delegate) {
        super(delegate);
    }

}
