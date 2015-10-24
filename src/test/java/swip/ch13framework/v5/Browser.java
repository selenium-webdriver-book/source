package swip.ch13framework.v5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Browser extends DelegatingWebDriver implements SearchScope {

    public Browser(WebDriver driver) {
        super(driver);
    } // <1>

    @Override
    public Element findElement(By by) {
        return new Element(super.findElement(by)); // <2>
    }
}
