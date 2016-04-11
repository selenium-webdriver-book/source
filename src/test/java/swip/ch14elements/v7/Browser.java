package swip.ch14elements.v7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch14elements.framework.DelegatingWebDriver;
import swip.ch14elements.framework.Element;

public class Browser extends DelegatingWebDriver  {

    public Browser(WebDriver driver) {
        super(driver);
    }

    public void setInputText(By by, String value) {
        Element element = findElement(by);
        element.clear();
        element.sendKeys(value);
    }
}