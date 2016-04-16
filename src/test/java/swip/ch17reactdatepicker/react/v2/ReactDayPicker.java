package swip.ch17reactdatepicker.react.v2;

import swip.framework.Browser;

import static swip.locators.ReactByClassName.CALENDAR;
import static swip.locators.ReactPredicates.CALENDAR_CLOSED;
import static swip.locators.TagName.DIV;

public class ReactDayPicker {

    private final Browser browser;

    public ReactDayPicker(Browser browser) {
        this.browser = browser;
    }

    public void pickDay(int day) {
        browser.untilFound(CALENDAR).findElements(DIV)
            .filter(e -> e.getText().equals(String.valueOf(day)))
            .findFirst()
            .get()
            .click();
        browser.until(CALENDAR_CLOSED);
    }
}
