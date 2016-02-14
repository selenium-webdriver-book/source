package swip.ch17datepicker.jquerydatepicker;


import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.className;

/**
 * This enum is a Supplier of ByClassName from Selenium By API.
 */
public enum JQueyByClassName implements Supplier<By> {

    UI_DATEPICKER_CALENDAR("ui-datepicker-calendar"),
    UI_DATEPICKER_NEXT("ui-datepicker-next"),
    UI_DATEPICKER_PREV("ui-datepicker-prev"),
    UI_DATEPICKER_MONTH("ui-datepicker-month"),
    UI_DATEPICKER_YEAR("ui-datepicker-year");

    private final By by;

    JQueyByClassName(String id) {
        this.by = className(id);
    }

    /**
     * @return the by instance variable which is a ByClassName.
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
