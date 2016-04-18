package swip.locators;

import org.openqa.selenium.By;

import java.util.function.Supplier;

/**
 * This enum is a Supplier of ByName from Selenium By API.
 */
public enum Name implements Supplier<By> {

    MAILING_OPTION("customFieldDS.customfield_ROW0_value"),;

    private final By by;

    Name(String id) {
        this.by = By.name(id);
    }

    /**
     * @return the by instance variable which is a ByName.
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