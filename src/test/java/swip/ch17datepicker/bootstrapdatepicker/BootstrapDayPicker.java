package swip.ch17datepicker.bootstrapdatepicker;


import swip.ch15pageflow.framework.v2.Browser;
import swip.ch15pageflow.framework.v2.Element;
import swip.ch15pageflow.locators.TagName;
import swip.ch17datepicker.datepicker.DayPicker;

import java.util.function.Predicate;

import static swip.ch15pageflow.locators.TagName.TD;
import static swip.ch17datepicker.bootstrapdatepicker.BootstrapByClassName.CALENDAR;

public class BootstrapDayPicker implements DayPicker {

    private Browser browser;
    private Predicate calendarClosed;

    public BootstrapDayPicker(Browser browser, Predicate calendarClosed) {
        this.browser = browser;
        this.calendarClosed = calendarClosed;
    }

    public void pick(int day) {
        browser.untilFound(CALENDAR)
            .findElements(TD)
            .filter((Element e) -> e.getText().equals(String.valueOf(day)))
            .findFirst()
            .get()
            .click();
        browser.untilFound(TagName.FORM).click();
        browser.until(calendarClosed);
    }
}
