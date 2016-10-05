package swb.locators;

import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.linkText;

/**
 * This enum is a Supplier of ByCssSelector from Selenium By API.
 */
public enum LinkText implements Supplier<By> {

    CHOOSE_LOCATION("choose location"),
    MEXICO("MEXICO"),
    CANCUN("Cancun"),

    ;

    private final By by;

    LinkText(String id) {
        this.by = linkText(id);
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
