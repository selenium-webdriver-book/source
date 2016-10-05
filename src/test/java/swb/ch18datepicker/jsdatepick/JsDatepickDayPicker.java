package swb.ch18datepicker.jsdatepick;

import swb.framework.Browser;
import swb.framework.ElementVisible;
import swb.framework.datepicker.DayPicker;


import static swb.locators.jsdatepick.JsDatepickByClassName.CALENDAR;
import static swb.locators.TagName.DIV;

public class JsDatepickDayPicker implements DayPicker {

    private Browser browser;

    public JsDatepickDayPicker(Browser browser) {
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
