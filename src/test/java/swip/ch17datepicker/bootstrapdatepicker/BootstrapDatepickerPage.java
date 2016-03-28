package swip.ch17datepicker.bootstrapdatepicker;


import swip.ch15pageflow.v2.framework.Browser;
import swip.ch17datepicker.datepicker.Calendar;
import swip.ch17datepicker.datepicker.CalendarPicker;
import swip.ch17datepicker.datepicker.Datepicker;

import java.time.Month;

import static swip.ch17datepicker.bootstrapdatepicker.BootstrapByClassName.DATE_FIELD;
import static swip.ch17datepicker.bootstrapdatepicker.BootstrapCalendarControls.*;
import static swip.ch17datepicker.bootstrapdatepicker.BootstrapCalendarDisplayValue.DISPLAY_MONTH;
import static swip.ch17datepicker.bootstrapdatepicker.BootstrapCalendarDisplayValue.DISPLAY_YEAR;


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
        return browser.getInputText(DATE_FIELD);
    }

}
