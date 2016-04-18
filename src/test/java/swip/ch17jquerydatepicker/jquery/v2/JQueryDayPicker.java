package swip.ch17jquerydatepicker.jquery.v2;

import swip.framework.Browser;
import swip.framework.ElementVisible;
import swip.locators.jquery.JQueryById;

import static org.openqa.selenium.By.linkText;
import static swip.locators.jquery.JQueryById.CALENDAR;

public class JQueryDayPicker {

    private final Browser browser;

    public JQueryDayPicker(Browser browser) {
        this.browser = browser;
    }

    public void pickDay(int day) {
        browser.untilFound(JQueryById.CALENDAR)
            .click(() -> linkText(String.valueOf(day))); //<9>
        browser.until(new ElementVisible(CALENDAR).negate());  //<11>
    }
}
