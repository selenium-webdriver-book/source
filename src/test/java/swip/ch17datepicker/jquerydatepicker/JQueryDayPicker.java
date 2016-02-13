package swip.ch17datepicker.jquerydatepicker;


import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.Element;
import swip.ch17datepicker.PagePredicates;
import swip.ch17datepicker.datepicker.DayPicker;

import static swip.ch15pageflow.locators.TagName.TD;
import static swip.ch17datepicker.jquerydatepicker.ClassName.UI_DATEPICKER_CALENDAR;

public class JQueryDayPicker implements DayPicker {

    private Browser browser;
    private PagePredicates jqueryCalendarNotDisplayed;

    public JQueryDayPicker(Browser browser, PagePredicates jqueryCalendarNotDisplayed) {
        this.browser = browser;
        this.jqueryCalendarNotDisplayed = jqueryCalendarNotDisplayed;
    }

    public void pick(int day) {
        browser.untilFound(Id.UI_DATEPICKER_DIV)
            .untilFound(UI_DATEPICKER_CALENDAR)
            .findElements(TD)
            .filter((Element e) -> Integer.parseInt(e.getText()) == day)
            .findFirst()
            .get()
            .click();
        browser.until(jqueryCalendarNotDisplayed);
    }
}
