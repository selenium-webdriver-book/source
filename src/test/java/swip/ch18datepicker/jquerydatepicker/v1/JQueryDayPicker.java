package swip.ch18datepicker.jquerydatepicker.v1;

import swip.ch15pageflow.v2.framework.Browser;

import static org.openqa.selenium.By.linkText;
import static swip.ch17datepicker.JQueryById.CALENDAR;
import static swip.ch17datepicker.JQueryPredicates.CALENDAR_CLOSED;

public class JQueryDayPicker implements DayPicker {

    private final Browser browser;

    public JQueryDayPicker(Browser browser) {
        this.browser = browser;
    }

    public void pickDay(int day) {
        browser.untilFound(CALENDAR)
            .click(() -> linkText(String.valueOf(day))); //<9>
        browser.until(CALENDAR_CLOSED);  //<11>
    }
}
