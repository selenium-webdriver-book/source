package swip.ch17datepicker.jquerydatepicker;


import swip.ch15pageflow.framework.Browser;
import swip.ch17datepicker.datepicker.CalendarPicker;
import swip.ch17datepicker.datepicker.Datepicker;

import java.time.Month;

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
        this.datepicker =
            new Datepicker(browser,
                TRIGGER,
                new CalendarPicker(browser, PREVIOUS_YEAR, NEXT_YEAR, DISPLAYED_YEAR),
                new CalendarPicker(browser, PREVIOUS_MONTH, NEXT_MONTH, DISPLAYED_MONTH),
                new JQueryDayPicker(browser, JQUERY_CALENDAR_NOT_DISPLAYED)
            );
    }


    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }

    public String getDate() {
        return browser.getInputText(DATE_PICKER);
    }

}
