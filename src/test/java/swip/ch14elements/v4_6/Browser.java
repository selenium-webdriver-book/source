package swip.ch14elements.v4_6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import swip.ch13framework.v4.DelegatingWebDriver;
import swip.ch13framework.v4.Element;
import swip.ch13framework.v4.SearchScope;

import java.util.function.Supplier;

public class Browser extends DelegatingWebDriver {

    public Browser(WebDriver driver) {
        super(driver);
    }

    public void setInputText(Supplier<By> by, String value) {
        Element element = findElement(by);
        element.clear();
        element.sendKeys(value);
    }

    public Select getSelectLambda(Supplier<By> by) {
        Element element = await(by);
        await((SearchScope driver) -> {
            element.click();
            return !element.findElements(By.tagName("option"))
                .isEmpty();
        });
        return new Select(element);
    }
}