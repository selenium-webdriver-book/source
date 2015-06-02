package swip.ch12framework.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Element {
    private final WebElement webElement; // <1>

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }

    public void click() {
        webElement.click();
    }

    public Element findElement(By by) {
        return new Element(webElement.findElement(by)); // <2>
    }

    public String getText() {
        return webElement.getText();
    }
}
