package swip.ch17datepicker.jquerydatepicker;

import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.id;

/**
 * This enum is a Supplier of ById from Selenium By API.
 */
public enum Id implements Supplier<By> {

    UI_DATEPICKER_DIV("ui-datepicker-div"),
    DATE_PICKER("datepicker");

    private final By by;

    Id(String id) {
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