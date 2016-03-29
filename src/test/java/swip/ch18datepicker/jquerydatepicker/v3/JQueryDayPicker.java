package swip.ch18datepicker.jquerydatepicker.v3;

import swip.ch15pageflow.v2.framework.Browser;

import static org.openqa.selenium.By.linkText;
import static swip.ch17datepicker.jquerydatepicker.JQueryById.UI_DATEPICKER_DIV;
import static swip.ch17datepicker.jquerydatepicker.JQueryPredicates.CALENDAR_CLOSED;

public class JQueryDayPicker implements DayPicker {

    private final Browser browser;

    public JQueryDayPicker(Browser browser) {
        this.browser = browser;
    }

    public void pickDay(int day) {
        browser.untilFound(UI_DATEPICKER_DIV)
            .click(() -> linkText(String.valueOf(day))); //<9>
        browser.until(CALENDAR_CLOSED);  //<11>
    }
}
