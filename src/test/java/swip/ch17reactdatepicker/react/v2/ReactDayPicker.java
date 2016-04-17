package swip.ch17reactdatepicker.react.v2;

import swip.framework.Browser;
import swip.framework.ElementNotVisible;

import static swip.locators.TagName.DIV;
import static swip.locators.react.ReactByClassName.CALENDAR;

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
        browser.until(new ElementNotVisible(CALENDAR));
    }
}
