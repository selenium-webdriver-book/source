package swb.locators.jsdatepick;

import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.className;

/**
 * This enum is a Supplier of ByClassName from Selenium By API.
 */
public enum JsDatepickByClassName implements Supplier<By> {     //<6>

    CALENDAR("boxMainInner"),            //<1>
    NEXT_MONTH_BUTTON("monthForwardButton"),              //<2>
    PREV_MONTH_BUTTON("monthBackwardButton"),              //<3>
    NEXT_YEAR_BUTTON("yearForwardButton"),              //<2>
    PREV_YEAR_BUTTON("yearBackwardButton"),              //<3>
    DISPLAY_MONTH_YEAR("controlsBarText");      //<4>

    private final By by;

    JsDatepickByClassName(String id) {
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
