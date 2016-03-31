package swip.ch17jquerydatepicker.v2;

import swip.ch15pageflow.v2.framework.Browser;
import swip.ch17jquerydatepicker.JQueryById;
import swip.ch17jquerydatepicker.JQueryPredicates;

import static org.openqa.selenium.By.linkText;

public class JQueryDayPicker {

    private final Browser browser;

    public JQueryDayPicker(Browser browser) {
        this.browser = browser;
    }

    public void pickDay(int day) {
        browser.untilFound(JQueryById.UI_DATEPICKER_DIV)
            .click(() -> linkText(String.valueOf(day))); //<9>
        browser.until(JQueryPredicates.CALENDAR_CLOSED);  //<11>
    }
}
