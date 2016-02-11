package swip.ch17datepicker.jquerydatepicker;


import swip.ch15pageflow.framework.Browser;
import swip.ch17datepicker.datepicker.CalendarBuilder;
import swip.ch17datepicker.datepicker.Datepicker;

import java.time.Month;

import static swip.ch17datepicker.JQueryDayLocatorFactory.JQUERY_DAY;
import static swip.ch17datepicker.PagePredicates.JQUERY_CALENDAR_NOT_DISPLAYED;
import static swip.ch17datepicker.jquerydatepicker.CalendarClicker.*;
import static swip.ch17datepicker.jquerydatepicker.CalendarIntegerLocator.DISPLAYED_MONTH;
import static swip.ch17datepicker.jquerydatepicker.CalendarIntegerLocator.DISPLAYED_YEAR;
import static swip.ch17datepicker.jquerydatepicker.Id.DATE_PICKER;


public class JQueryDatePickerPage {

    private final Browser browser;

    private final Datepicker datepicker;

    public JQueryDatePickerPage(Browser browser) {
        this.browser = browser;
        this.datepicker = new Datepicker(
            new CalendarBuilder()
                .withPage(browser)
                .withTrigger(TRIGGER)
                .withDisplayedYear(DISPLAYED_YEAR)
                .withDisplayedMonth(DISPLAYED_MONTH)
                .withPreviousMonth(PREVIOUS_MONTH)
                .withNextMonth(NEXT_MONTH)
                .withDayLocatorFactory(JQUERY_DAY)
                .withCalendarClosed(JQUERY_CALENDAR_NOT_DISPLAYED)
                .build()
        );
    }


    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }

    public String getDate() {
        return browser.getInputText(DATE_PICKER);
    }

}
