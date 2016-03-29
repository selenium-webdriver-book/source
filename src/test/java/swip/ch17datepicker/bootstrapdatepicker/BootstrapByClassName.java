package swip.ch17datepicker.bootstrapdatepicker;


import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.className;

/**
 * This enum is a Supplier of ByClassName from Selenium By API.
 */
public enum BootstrapByClassName implements Supplier<By> {

    CALENDAR("datepicker-days"),
    DATE_FIELD("trigger"),
    NEXT_MONTH_BUTTON("next"),
    PREV_MONTH_BUTTON("prev"),
    DISPLAY_MONTH_YEAR("datepicker-switch");

    private final By by;

    BootstrapByClassName(String id) {
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
