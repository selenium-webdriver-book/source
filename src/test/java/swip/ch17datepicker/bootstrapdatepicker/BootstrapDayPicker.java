package swip.ch17datepicker.bootstrapdatepicker;


import swip.ch15pageflow.v2.framework.Browser;
import swip.ch15pageflow.v2.framework.Element;
import swip.ch15pageflow.v2.framework.ExplicitWait;
import swip.ch17datepicker.datepicker.DayPicker;

import java.util.function.Predicate;

import static swip.ch15pageflow.locators.TagName.FORM;
import static swip.ch15pageflow.locators.TagName.TD;
import static swip.ch17datepicker.bootstrapdatepicker.BootstrapByClassName.CALENDAR;

public class BootstrapDayPicker implements DayPicker {

    private Browser browser;
    private Predicate<ExplicitWait> calendarClosed;

    public BootstrapDayPicker(Browser browser, Predicate<ExplicitWait> calendarClosed) {
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
        browser.click(FORM);
        browser.until(calendarClosed);
    }
}
