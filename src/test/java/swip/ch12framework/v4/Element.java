package swip.ch12framework.v4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Element implements ExplicitWait, SearchScope {
    private final WebElement webElement;

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }

    public void click() {
        webElement.click();
    }

    @Override
    public Element findElement(By by) {
        return new Element(webElement.findElement(by));
    }

    public String getText() {
        return webElement.getText();
    }


}
