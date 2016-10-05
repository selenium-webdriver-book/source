package swb.ch18datepicker.react;

import swb.framework.Browser;
import swb.framework.ElementVisible;
import swb.framework.datepicker.DayPicker;

import static swb.locators.TagName.DIV;
import static swb.locators.react.ReactByClassName.CALENDAR;

public class ReactDayPicker implements DayPicker {

    private Browser browser;

    public ReactDayPicker(Browser browser) {
        this.browser = browser;
    }

    @Override
    public void pick(int day) {
        browser.await(CALENDAR).findElements(DIV)
            .filter(e -> e.getText().equals(String.valueOf(day)))
            .findFirst()
            .get()
            .click();
        browser.await(new ElementVisible(CALENDAR).negate());
    }
}
