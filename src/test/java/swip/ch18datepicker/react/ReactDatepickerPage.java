package swip.ch18datepicker.react;

import swip.framework.Browser;
import swip.framework.datepicker.Calendar;
import swip.framework.datepicker.CalendarPicker;
import swip.framework.datepicker.Datepicker;

import java.time.Month;

import static swip.ch18datepicker.react.ReactCalendarControls.*;
import static swip.ch18datepicker.react.ReactCalendarDisplayValue.DISPLAY_MONTH;
import static swip.ch18datepicker.react.ReactCalendarDisplayValue.DISPLAY_YEAR;
import static swip.locators.react.ReactByXpath.TRIGGER_BY;

public class ReactDatepickerPage {

    private final Browser browser;
    private final Datepicker datepicker;

    public ReactDatepickerPage(Browser browser) {
        this.browser = browser;
        this.datepicker = new Datepicker(
            new Calendar(browser, TRIGGER),
            new CalendarPicker(browser, PREVIOUS_YEAR, NEXT_YEAR, DISPLAY_YEAR),
            new CalendarPicker(browser, PREVIOUS_MONTH, NEXT_MONTH, DISPLAY_MONTH),
            new ReactDayPicker(browser)
        );
    }

    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }

    public String getDate() {
        return browser.getInputText(TRIGGER_BY);
    }

}
