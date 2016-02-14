package swip.ch17datepicker.bootstrapdatepicker;


import swip.ch15pageflow.framework.Browser;
import swip.ch17datepicker.datepicker.CalendarPicker;
import swip.ch17datepicker.datepicker.Datepicker;

import java.time.Month;

import static swip.ch17datepicker.bootstrapdatepicker.BootstrapByClassName.DATE_FIELD;
import static swip.ch17datepicker.bootstrapdatepicker.BootstrapCalendarControls.*;
import static swip.ch17datepicker.bootstrapdatepicker.BootstrapCalendarDisplayValue.MONTH;
import static swip.ch17datepicker.bootstrapdatepicker.BootstrapCalendarDisplayValue.YEAR;
import static swip.ch17datepicker.bootstrapdatepicker.BootstrapPredicates.CALENDAR_CLOSED;


public class BootstrapDatepickerPage {

    private final Browser browser;

    private final Datepicker datepicker;

    public BootstrapDatepickerPage(Browser browser) {
        this.browser = browser;
        this.datepicker = new Datepicker(
            browser,
            TRIGGER,
            new CalendarPicker(browser, PREVIOUS_YEAR, NEXT_YEAR, YEAR),
            new CalendarPicker(browser, PREVIOUS_MONTH, NEXT_MONTH, MONTH),
            new BootstrapDayPicker(browser, CALENDAR_CLOSED)
        );
    }


    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }

    public String getDate() {
        return browser.getInputText(DATE_FIELD);
    }

}
