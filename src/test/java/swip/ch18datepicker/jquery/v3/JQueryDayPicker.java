package swip.ch18datepicker.jquery.v3;

import swip.framework.Browser;
import swip.framework.ElementNotVisible;
import swip.framework.datepicker.DayPicker;

import static org.openqa.selenium.By.linkText;
import static swip.locators.jquery.JQueryById.CALENDAR;

public class JQueryDayPicker implements DayPicker {

    private final Browser browser;

    public JQueryDayPicker(Browser browser) {
        this.browser = browser;
    }

    @Override
    public void pick(int day) {
        browser.untilFound(CALENDAR)
            .click(() -> linkText(String.valueOf(day))); //<9>
        browser.until(new ElementNotVisible(CALENDAR));  //<11>
    }
}
