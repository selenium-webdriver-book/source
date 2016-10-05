package swb.locators.jsdatepick;

import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.id;

/**
 * This enum is a Supplier of ById from Selenium By API.
 */
public enum JsDatepickById implements Supplier<By> {

    TRIGGER_BY("inputField");

    private final By by;

    JsDatepickById(String id) {
        this.by = id(id);
    }

    /**
     * @return the by instance variable which is a ById.
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