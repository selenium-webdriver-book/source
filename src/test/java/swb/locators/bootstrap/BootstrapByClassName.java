package swb.locators.bootstrap;

import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.className;

/**
 * This enum is a Supplier of ByClassName from Selenium By API.
 */
public enum BootstrapByClassName implements Supplier<By> {     //<6>

    CALENDAR("datepicker-days"),            //<1>
    TRIGGER_BY("trigger"),                  //<2>
    NEXT_MONTH_BUTTON("next"),              //<3>
    PREV_MONTH_BUTTON("prev"),              //<4>
    DISPLAY_MONTH_YEAR("datepicker-switch");      //<5>

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
