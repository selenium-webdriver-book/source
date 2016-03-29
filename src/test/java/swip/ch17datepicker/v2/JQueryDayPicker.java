package swip.ch17datepicker.v2;

import swip.ch15pageflow.v2.framework.Browser;
import swip.ch17datepicker.JQueryById;
import swip.ch17datepicker.JQueryPredicates;

import static org.openqa.selenium.By.linkText;

public class JQueryDayPicker {

    private final Browser browser;

    public JQueryDayPicker(Browser browser) {
        this.browser = browser;
    }

    public void pickDay(int day) {
        browser.untilFound(JQueryById.CALENDAR)
            .click(() -> linkText(String.valueOf(day))); //<9>
        browser.until(JQueryPredicates.CALENDAR_CLOSED);  //<11>
    }
}
