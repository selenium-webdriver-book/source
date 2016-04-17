package swip.ch18datepicker.bootstrap;

import swip.framework.Browser;
import swip.framework.datepicker.Calendar;
import swip.framework.datepicker.CalendarPicker;
import swip.framework.datepicker.Datepicker;

import java.time.Month;

import static swip.locators.bootstrap.BootstrapByClassName.TRIGGER_BY;
import static swip.ch18datepicker.bootstrap.BootstrapCalendarControls.*;
import static swip.ch18datepicker.bootstrap.BootstrapCalendarDisplayValue.DISPLAY_MONTH;
import static swip.ch18datepicker.bootstrap.BootstrapCalendarDisplayValue.DISPLAY_YEAR;

public class BootstrapDatepickerPage {

    private final Browser browser;
    private final Datepicker datepicker;

    public BootstrapDatepickerPage(Browser browser) {
        this.browser = browser;
        this.datepicker = new Datepicker(
            new Calendar(browser, TRIGGER),
            new CalendarPicker(browser, PREVIOUS_YEAR, NEXT_YEAR, DISPLAY_YEAR),
            new CalendarPicker(browser, PREVIOUS_MONTH, NEXT_MONTH, DISPLAY_MONTH),
            new BootstrapDayPicker(browser)
        );
    }

    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }

    public String getDate() {
        return browser.getInputText(TRIGGER_BY);
    }

}
