package swip.ch18datepicker.react;

import swip.framework.Browser;
import swip.framework.ElementVisible;
import swip.framework.datepicker.DayPicker;

import static swip.locators.TagName.DIV;
import static swip.locators.react.ReactByClassName.CALENDAR;

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
        browser.until(new ElementVisible(CALENDAR).negate());
    }
}
