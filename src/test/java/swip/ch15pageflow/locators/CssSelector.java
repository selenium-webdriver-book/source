package swip.ch15pageflow.locators;

import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.cssSelector;

/**
 * This enum is a Supplier of ByCssSelector from Selenium By API.
 */
public enum CssSelector implements Supplier<By> {

    CONTINUE("input[value='Continue']");

    private final By by;

    CssSelector(String id) {
        this.by = cssSelector(id);
    }

    /**
     * @return the by instance variable which is a ByCssSelector.
     */
    @Override
    public By get() {
        return by;
    }

    @Override
    public String toString() {
        return by.toString();
    }
}
