package swip.ch17datepicker.bootstrapdatepicker;


import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.Element;
import swip.ch15pageflow.locators.TagName;
import swip.ch17datepicker.datepicker.DayPicker;

import java.util.function.Predicate;

import static swip.ch15pageflow.locators.TagName.TD;
import static swip.ch17datepicker.bootstrapdatepicker.BootstrapByClassName.CALENDAR;

public class BootstrapDayPicker implements DayPicker {

    private Browser browser;                       //<1>
    private Predicate reactCalendarClosed;  //<2>

    public BootstrapDayPicker(Browser browser, Predicate reactCalendarClosed) { //<3>
        this.browser = browser;
        this.reactCalendarClosed = reactCalendarClosed;
    }

    public void pick(int day) {
        browser.untilFound(CALENDAR)     //<4>
            .findElements(TD)                        //<6>
            .filter((Element e) -> e.getText().equals(String.valueOf(day)))  //<7>
            .findFirst()                     //<8>
            .get()                           //<9>
            .click();                        //<10>
        browser.untilFound(TagName.FORM).click();
        browser.until(reactCalendarClosed);  //<11>
    }
}
