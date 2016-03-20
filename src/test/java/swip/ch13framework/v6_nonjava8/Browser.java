package swip.ch13framework.v6_nonjava8;

import org.openqa.selenium.WebDriver;

public class Browser extends DelegatingWebDriver {

    public Browser(WebDriver driver) {
        super(driver);
    } // <1>
}
