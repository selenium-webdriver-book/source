package swip.ch14elements.framework;

import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class Browser extends DelegatingWebDriver implements HasInputDevices {

    public Browser(WebDriver driver) {
        super(driver);
    }

    public void setInputText(By by, String value) {
        Retry retry = new Retry(5, 1, TimeUnit.SECONDS);

        retry.attempt(
            new Attemptable() {
                @Override
                public void attempt() throws Exception {
                    Element element = findElement(by);
                    element.clear();
                    element.sendKeys(value);
                    assert value.equals(element.getAttribute("value"));
                }
            }
        );
    }

    public void setInputTextLambda(By by, String value) {
        Retry retry = new Retry(5, 1, TimeUnit.SECONDS);

        retry.attempt(
            () -> {
                Element element = findElement(by);
                element.clear();
                element.sendKeys(value);
                assert value.equals(element.getAttribute("value"));
            }
        );
    }

    public String getInputText(By by) {
        return untilFound(by).getAttribute("value");
    }

    public void setCheckboxValue(By by, boolean value) {
        Element checkbox = untilFound(by);
        if (checkbox.isSelected() != value) {
            checkbox.click();
        }
    }

    public boolean isChecked(By by) {
        return untilFound(by).isSelected();
    }

    public void setRadio(By by, String value) {
        List<WebElement> radiobuttons = findElements(by);

        assert radiobuttons.size() >= 2;

        for (WebElement e : radiobuttons) {
            if (e.getAttribute("value").equals(value)) {
                e.click();
                return;
            }
        }
        throw new IllegalArgumentException(
            "unable to find element with value " + value);
    }

    public String getRadio(By by) {
        List<WebElement> radiobuttons = findElements(by);

        assert radiobuttons.size() >= 2;

        for (WebElement e : radiobuttons) {
            if (Boolean.valueOf(e.getAttribute("checked"))) {
                return e.getAttribute("value");
            }
        }
        return null;
    }

    public Select getSelect(By by) {
        final Element element = untilFound(by);
        new WebDriverWait(this, 3, 100)
            .until(new Predicate<WebDriver>() {
                @Override
                public boolean apply(WebDriver driver) {
                    element.click();
                    return !element.findElements(By.tagName("option")).isEmpty();
                }
            });
        return new Select(element);
    }

    public Select getSelectLambda(By by) {
        Element element = untilFound(by);
        new WebDriverWait(this, 3, 100)
            .until((WebDriver driver) -> {
                element.click();
                return !element.findElements(By.tagName("option")).isEmpty();
            });
        return new Select(element);
    }

    @Override
    public Keyboard getKeyboard() {
        return ((HasInputDevices) delegate).getKeyboard();
    }

    @Override
    public Mouse getMouse() {
        return ((HasInputDevices) delegate).getMouse();
    }

    public void doubleClick(By by) {
        Element element = untilFound(by);
        new Actions(delegate) // #C create actions from the driver
            .doubleClick(element) // #D add a double-click to the sequence
            .perform(); // #E perform the sequence
    }
}