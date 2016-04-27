package swip.ch13framework.v1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

public class Element implements ExplicitWait {

    private final WebElement webElement;

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }

    @Override
    public WebElement findElement(Supplier<By> by) {
        return webElement.findElement(by.get());
    }
}
