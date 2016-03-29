package swip.ch18datepicker.bootstrapdatepicker;


import swip.ch18datepicker.datepicker.DayPicker;
import swip.ch15pageflow.v2.framework.Browser;
import swip.ch15pageflow.v2.framework.Element;

import static swip.ch15pageflow.locators.TagName.FORM;
import static swip.ch15pageflow.locators.TagName.TD;
import static swip.ch18datepicker.bootstrapdatepicker.BootstrapByClassName.CALENDAR;
import static swip.ch18datepicker.bootstrapdatepicker.BootstrapPredicates.CALENDAR_CLOSED;

public class BootstrapDayPicker implements DayPicker {

    private Browser browser;

    public BootstrapDayPicker(Browser browser) {
        this.browser = browser;
    }

    public void pick(int day) {
        browser.untilFound(CALENDAR)
            .findElements(TD)
            .filter((Element e) -> e.getText().equals(String.valueOf(day)))
            .findFirst()
            .get()
            .click();
        browser.click(FORM);
        browser.until(CALENDAR_CLOSED);
    }
}
