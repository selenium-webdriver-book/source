package swip.ch17datepicker.reactdatepicker;


import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.className;

/**
 * This enum is a Supplier of ByClassName from Selenium By API.
 */
public enum ReactClassName implements Supplier<By> {

    CALENDAR("datepicker"),
    TRIGGER("datepicker__input"),
    UI_DATEPICKER_NEXT("datepicker__navigation--next"),
    UI_DATEPICKER_PREV("datepicker__navigation--previous"),
    UI_DATEPICKER_MONTH_YEAR("datepicker__current-month");

    private final By by;

    ReactClassName(String id) {
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
