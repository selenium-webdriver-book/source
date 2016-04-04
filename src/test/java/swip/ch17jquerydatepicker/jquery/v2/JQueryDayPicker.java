package swip.ch17jquerydatepicker.jquery.v2;

import swip.ch15pageflow.framework.Browser;
import swip.ch17jquerydatepicker.locators.JQueryById;

import static org.openqa.selenium.By.linkText;
import static swip.ch17jquerydatepicker.locators.JQueryPredicates.CALENDAR_CLOSED;

public class JQueryDayPicker {

    private final Browser browser;

    public JQueryDayPicker(Browser browser) {
        this.browser = browser;
    }

    public void pickDay(int day) {
        browser.untilFound(JQueryById.CALENDAR)
            .click(() -> linkText(String.valueOf(day))); //<9>
        browser.until(CALENDAR_CLOSED);  //<11>
    }
}
