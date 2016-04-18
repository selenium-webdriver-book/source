package swip.ch13framework.v1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Element implements ExplicitWait {

    private final WebElement webElement;

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }

    @Override
    public WebElement findElement(By by) {
        return webElement.findElement(by);
    }
}
