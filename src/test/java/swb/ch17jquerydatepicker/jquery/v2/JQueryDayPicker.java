package swb.ch17jquerydatepicker.jquery.v2;

import swb.framework.Browser;
import swb.framework.ElementVisible;
import swb.locators.jquery.JQueryById;

import static org.openqa.selenium.By.linkText;
import static swb.locators.jquery.JQueryById.CALENDAR;

public class JQueryDayPicker {

    private final Browser browser;

    public JQueryDayPicker(Browser browser) {
        this.browser = browser;
    }

    public void pickDay(int day) {
        browser.await(JQueryById.CALENDAR)
            .click(() -> linkText(String.valueOf(day))); //<9>
        browser.await(new ElementVisible(CALENDAR).negate());  //<11>
    }
}
