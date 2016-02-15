package swip.ch17datepicker.jquerydatepicker.v5;


import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.Element;
import swip.ch17datepicker.datepicker.DayPicker;

import java.util.function.Predicate;

import static swip.ch15pageflow.locators.TagName.TD;
import static swip.ch17datepicker.jquerydatepicker.v3.JQueryByClassName.CALENDAR;
import static swip.ch17datepicker.jquerydatepicker.v3.JQueryById.UI_DATEPICKER_DIV;

public class JQueryDayPicker implements DayPicker {

    private Browser browser;                       //<1>
    private Predicate calendarClosed;  //<2>

    public JQueryDayPicker(Browser browser, Predicate calendarClosed) { //<3>
        this.browser = browser;
        this.calendarClosed = calendarClosed;
    }

    public void pick(int day) {
        browser.untilFound(UI_DATEPICKER_DIV)     //<4>
            .untilFound(CALENDAR)      //<5>
            .findElements(TD)                        //<6>
            .filter((Element e) -> Integer.parseInt(e.getText()) == day)  //<7>
            .findFirst()                     //<8>
            .get()                           //<9>
            .click();                        //<10>
        browser.until(calendarClosed);  //<11>
    }
}
