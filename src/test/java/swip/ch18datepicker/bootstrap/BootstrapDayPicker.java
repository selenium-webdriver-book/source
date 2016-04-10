package swip.ch18datepicker.bootstrap;

import swip.ch15pageflow.framework.Browser;
import swip.ch18datepicker.datepicker.DayPicker;

import static swip.ch15pageflow.locators.TagName.FORM;
import static swip.ch15pageflow.locators.TagName.TD;
import static swip.ch18datepicker.bootstrap.BootstrapByClassName.CALENDAR;
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
