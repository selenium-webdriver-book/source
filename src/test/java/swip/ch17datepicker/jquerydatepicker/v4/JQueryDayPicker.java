package swip.ch17datepicker.jquerydatepicker.v4;


import swip.ch15pageflow.v2.framework.Browser;
import swip.ch17datepicker.datepicker.DayPicker;

import static org.openqa.selenium.By.linkText;
import static swip.ch17datepicker.jquerydatepicker.JQueryById.UI_DATEPICKER_DIV;
import static swip.ch17datepicker.jquerydatepicker.JQueryPredicates.CALENDAR_CLOSED;

public class JQueryDayPicker implements DayPicker {

    private Browser browser;                       //<1>

    public JQueryDayPicker(Browser browser) { //<2>
        this.browser = browser;
    }

    public void pick(int day) {
        browser.untilFound(UI_DATEPICKER_DIV)
            .click(() -> linkText(String.valueOf(day))); //<9>
        browser.until(CALENDAR_CLOSED);  //<11>
    }
}
