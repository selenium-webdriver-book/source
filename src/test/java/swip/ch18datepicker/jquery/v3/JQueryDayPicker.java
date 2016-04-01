package swip.ch18datepicker.jquery.v3;


import swip.ch18datepicker.framework.Browser;

import static org.openqa.selenium.By.linkText;
import static swip.ch17jquerydatepicker.JQueryById.UI_DATEPICKER_DIV;
import static swip.ch18datepicker.jquery.JQueryPredicates.CALENDAR_CLOSED;


public class JQueryDayPicker implements DayPicker {

    private final Browser browser;

    public JQueryDayPicker(Browser browser) {
        this.browser = browser;
    }

    public void pick(int day) {
        browser.untilFound(UI_DATEPICKER_DIV)
            .click(() -> linkText(String.valueOf(day))); //<9>
        browser.until(CALENDAR_CLOSED);  //<11>
    }
}
