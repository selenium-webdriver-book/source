package swip.ch17datepicker.jquerydatepicker.v4;


import swip.ch15pageflow.v2.framework.Browser;
import swip.ch15pageflow.v2.framework.Element;
import swip.ch17datepicker.datepicker.DayPicker;

import static swip.ch15pageflow.locators.TagName.TD;
import static swip.ch17datepicker.jquerydatepicker.JQueryByClassName.CALENDAR;
import static swip.ch17datepicker.jquerydatepicker.JQueryById.UI_DATEPICKER_DIV;
import static swip.ch17datepicker.jquerydatepicker.JQueryPredicates.CALENDAR_CLOSED;

public class JQueryDayPicker implements DayPicker {

    private Browser browser;                       //<1>

    public JQueryDayPicker(Browser browser) { //<2>
        this.browser = browser;
    }

    public void pick(int day) {
        browser.untilFound(UI_DATEPICKER_DIV)     //<3>
            .untilFound(CALENDAR)      //<4>
            .findElements(TD)                        //<5>
            .filter((Element e) -> Integer.parseInt(e.getText()) == day)  //<6>
            .findFirst()                     //<7>
            .get()                           //<8>
            .click();                        //<9>
        browser.until(CALENDAR_CLOSED);  //<10>
    }
}
