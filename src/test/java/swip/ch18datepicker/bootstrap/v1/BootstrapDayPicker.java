package swip.ch18datepicker.bootstrap.v1;

import swip.framework.Browser;
import swip.framework.ElementVisible;
import swip.framework.datepicker.DayPicker;

import static swip.locators.TagName.FORM;
import static swip.locators.TagName.TD;
import static swip.locators.bootstrap.BootstrapByClassName.CALENDAR;

public class BootstrapDayPicker implements DayPicker {

    private Browser browser;

    public BootstrapDayPicker(Browser browser) {
        this.browser = browser;
    }

    @Override
    public void pick(int day) {
        browser.await(CALENDAR).findElements(TD)
            .filter(e -> e.getText().equals(String.valueOf(day)))
            .findFirst()
            .get()
            .click();
        browser.click(FORM);
        browser.await(new ElementVisible(CALENDAR).negate());
    }
}
