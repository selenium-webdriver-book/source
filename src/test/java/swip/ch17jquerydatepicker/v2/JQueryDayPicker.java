package swip.ch17jquerydatepicker.v2;

import swip.ch17jquerydatepicker.JQueryById;
import swip.ch17jquerydatepicker.framework.Browser;

import static org.openqa.selenium.By.linkText;
import static swip.ch17jquerydatepicker.JQueryPredicates.CALENDAR_CLOSED;

public class JQueryDayPicker {

    private final Browser browser;

    public JQueryDayPicker(Browser browser) {
        this.browser = browser;
    }

    public void pickDay(int day) {
        browser.untilFound(JQueryById.UI_DATEPICKER_DIV)
            .click(() -> linkText(String.valueOf(day))); //<9>
        browser.until(CALENDAR_CLOSED);  //<11>
    }
}
