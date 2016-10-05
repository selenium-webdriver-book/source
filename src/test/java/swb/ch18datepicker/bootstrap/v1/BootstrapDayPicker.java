package swb.ch18datepicker.bootstrap.v1;

import swb.framework.Browser;
import swb.framework.ElementVisible;
import swb.framework.datepicker.DayPicker;

import static swb.locators.TagName.FORM;
import static swb.locators.TagName.TD;
import static swb.locators.bootstrap.BootstrapByClassName.CALENDAR;

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
