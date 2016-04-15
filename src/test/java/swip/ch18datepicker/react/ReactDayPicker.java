package swip.ch18datepicker.react;

import swip.framework.Browser;
import swip.framework.datepicker.DayPicker;

import static swip.ch18datepicker.react.ReactPredicates.CALENDAR_CLOSED;
import static swip.locators.ReactByClassName.CALENDAR;
import static swip.locators.TagName.DIV;

public class ReactDayPicker implements DayPicker {

    private Browser browser;

    public ReactDayPicker(Browser browser) {
        this.browser = browser;
    }

    @Override
    public void pick(int day) {
        browser.untilFound(CALENDAR).findElements(DIV)
            .filter(e -> e.getText().equals(String.valueOf(day)))
            .findFirst()
            .get()
            .click();
        browser.until(CALENDAR_CLOSED);
    }
}
