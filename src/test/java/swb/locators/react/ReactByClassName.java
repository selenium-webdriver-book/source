package swb.locators.react;

import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.className;

/**
 * This enum is a Supplier of ByClassName from Selenium By API.
 */
public enum ReactByClassName implements Supplier<By> {     //<6>

    TRIGGER_CONTAINER("react-datepicker__input-container"),
    TRIGGER_BY("ignore-react-onclickoutside"),
    CALENDAR("react-datepicker"),            //<1>
    NEXT_MONTH_BUTTON("react-datepicker__navigation--next"),              //<2>
    PREV_MONTH_BUTTON("react-datepicker__navigation--previous"),              //<3>
    DISPLAY_MONTH_YEAR("react-datepicker__current-month");      //<4>

    private final By by;

    ReactByClassName(String id) {
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
