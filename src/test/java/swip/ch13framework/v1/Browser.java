package swip.ch13framework.v1;

import org.openqa.selenium.WebDriver;
import swip.ch12wrapping.v0_8.DelegatingWebDriver;

public class Browser extends DelegatingWebDriver
    implements ExplicitWait {

    public Browser(WebDriver driver) {
        super(driver);
    }
}
