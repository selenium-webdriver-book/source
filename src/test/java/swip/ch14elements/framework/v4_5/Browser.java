package swip.ch14elements.framework.v4_5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch13framework.v4.DelegatingWebDriver;
import swip.ch13framework.v4.Element;

public class Browser extends DelegatingWebDriver {

    public Browser(WebDriver driver) {
        super(driver);
    }

    public void setInputText(By by, String value) {
        Element element = findElement(by);
        element.clear();
        element.sendKeys(value);
    }
}