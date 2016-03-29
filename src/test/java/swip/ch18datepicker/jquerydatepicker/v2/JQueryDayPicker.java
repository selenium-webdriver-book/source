package swip.ch18datepicker.jquerydatepicker.v2;


import swip.ch18datepicker.datepicker.DayPicker;
import swip.ch15pageflow.v2.framework.Browser;

import static org.openqa.selenium.By.linkText;
import static swip.ch17datepicker.JQueryById.CALENDAR;
import static swip.ch17datepicker.JQueryPredicates.CALENDAR_CLOSED;

public class JQueryDayPicker implements DayPicker {

    private Browser browser;                       //<1>

    public JQueryDayPicker(Browser browser) { //<2>
        this.browser = browser;
    }

    public void pick(int day) {
        browser.untilFound(CALENDAR)
            .click(() -> linkText(String.valueOf(day))); //<9>
        browser.until(CALENDAR_CLOSED);  //<11>
    }
}
