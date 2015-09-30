package swip.ch13elements.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser extends DelegatingWebDriver implements ExplicitWait, SearchScope {

    public Browser(WebDriver driver) {
        super(driver);
    }

    @Override
    public Element findElement(By by) {
        return new Element(super.findElement(by));
    }

    public void setInputText(By by, String text) {
        Element element = untilFound(by);
        element.clear();
        element.sendKeys(text);
    }

    public void setCheckboxValue(By by, boolean value) {
        Element checkbox = untilFound(by);
        if (checkbox.isSelected() != value) {
            checkbox.click();
        }
    }

    public void setRadio(By by, String value) {
        findElements(by)
                .stream()
                .filter((e) -> e.getAttribute("value").equals(value))
                .findFirst()
                .get()
                .click();
    }

    public Select getSelect(By by) {
        Element element = untilFound(by);
        new WebDriverWait(this, 3, 100)
                .until((WebDriver driver) -> {
                    element.click();
                    return !element.findElements(By.tagName("option")).isEmpty();
                });
        return new Select(element);
    }
}