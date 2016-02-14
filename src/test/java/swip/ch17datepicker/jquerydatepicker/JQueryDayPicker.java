package swip.ch17datepicker.jquerydatepicker;


import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.Element;
import swip.ch17datepicker.datepicker.DayPicker;

import java.util.function.Predicate;

import static swip.ch15pageflow.locators.TagName.TD;
import static swip.ch17datepicker.jquerydatepicker.ClassName.UI_DATEPICKER_CALENDAR;

public class JQueryDayPicker implements DayPicker {

    private Browser browser;                       //<1>
    private Predicate jqueryCalendarNotDisplayed;  //<2>

    public JQueryDayPicker(Browser browser, Predicate jqueryCalendarNotDisplayed) { //<3>
        this.browser = browser;
        this.jqueryCalendarNotDisplayed = jqueryCalendarNotDisplayed;
    }

    public void pick(int day) {
        browser.untilFound(Id.UI_DATEPICKER_DIV)     //<4>
            .untilFound(UI_DATEPICKER_CALENDAR)      //<5>
            .findElements(TD)                        //<6>
            .filter((Element e) -> Integer.parseInt(e.getText()) == day)  //<7>
            .findFirst()                     //<8>
            .get()                           //<9>
            .click();                        //<10>
        browser.until(jqueryCalendarNotDisplayed);  //<11>
    }
}
