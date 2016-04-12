package swip.ch18datepicker.bootstrap;

import swip.framework.Browser;
import swip.framework.datepicker.DayPicker;

import static swip.locators.TagName.FORM;
import static swip.locators.TagName.TD;
import static swip.locators.BootstrapByClassName.CALENDAR;
import static swip.ch18datepicker.bootstrap.BootstrapPredicates.CALENDAR_CLOSED;

public class BootstrapDayPicker implements DayPicker {

    private Browser browser;

    public BootstrapDayPicker(Browser browser) {
        this.browser = browser;
    }

    @Override
    public void pick(int day) {
        browser.untilFound(CALENDAR).findElements(TD)
            .filter(e -> e.getText().equals(String.valueOf(day)))
            .findFirst()
            .get()
            .click();
        browser.click(FORM);
        browser.until(CALENDAR_CLOSED);
    }
}
