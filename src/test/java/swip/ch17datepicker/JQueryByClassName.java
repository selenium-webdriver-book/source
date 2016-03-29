package swip.ch17datepicker;


import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.className;

/**
 * This enum is a Supplier of ByClassName from Selenium By API.
 */
public enum JQueryByClassName implements Supplier<By> {

    NEXT_MONTH_BUTTON("ui-datepicker-next"), //<2>
    PREV_MONTH_BUTTON("ui-datepicker-prev"), //<3>
    DISPLAY_MONTH("ui-datepicker-month"),    //<4>
    DISPLAY_YEAR("ui-datepicker-year");      //<5>

    private final By by;

    JQueryByClassName(String className) {
        this.by = className(className);
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
