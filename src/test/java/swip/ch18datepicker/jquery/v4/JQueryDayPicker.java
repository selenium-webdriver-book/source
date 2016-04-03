package swip.ch18datepicker.jquery.v4;


import swip.ch15pageflow.v2.framework.Browser;

import static org.openqa.selenium.By.linkText;
import static swip.ch17jquerydatepicker.JQueryById.CALENDAR;
import static swip.ch17jquerydatepicker.JQueryPredicates.CALENDAR_CLOSED;


public class JQueryDayPicker implements DayPicker {

    private final Browser browser;

    public JQueryDayPicker(Browser browser) {
        this.browser = browser;
    }

    public void pick(int day) {
        browser.untilFound(CALENDAR)
            .click(() -> linkText(String.valueOf(day))); //<9>
        browser.until(CALENDAR_CLOSED);  //<11>
    }
}
