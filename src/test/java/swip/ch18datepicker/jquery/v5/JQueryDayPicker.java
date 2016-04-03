package swip.ch18datepicker.jquery.v5;


import swip.ch15pageflow.framework.Browser;
import swip.ch18datepicker.datepicker.DayPicker;

import static org.openqa.selenium.By.linkText;
import static swip.ch17jquerydatepicker.JQueryById.CALENDAR;
import static swip.ch17jquerydatepicker.JQueryPredicates.CALENDAR_CLOSED;

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
