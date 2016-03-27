package swip.ch17datepicker.jquerydatepicker.v4;


import swip.ch15pageflow.v2.framework.Browser;
import swip.ch17datepicker.datepicker.Calendar;
import swip.ch17datepicker.datepicker.CalendarPicker;
import swip.ch17datepicker.datepicker.Datepicker;

import java.time.Month;

import static swip.ch17datepicker.jquerydatepicker.JQueryById.DATE_FIELD;
import static swip.ch17datepicker.jquerydatepicker.v4.JQueryCalendarControls.*;
import static swip.ch17datepicker.jquerydatepicker.v4.JQueryCalendarDisplayValue.MONTH;
import static swip.ch17datepicker.jquerydatepicker.v4.JQueryCalendarDisplayValue.YEAR;


public class JQueryDatepickerPage {

    private final Browser browser;

    private final Datepicker datepicker;

    public JQueryDatepickerPage(Browser browser) {
        this.browser = browser;
        this.datepicker = new Datepicker(  //<1>
            new Calendar(browser, TRIGGER),        //<2>
            new CalendarPicker(browser, PREVIOUS_YEAR, NEXT_YEAR, YEAR),     //<3>
            new CalendarPicker(browser, PREVIOUS_MONTH, NEXT_MONTH, MONTH),  //<4>
            new JQueryDayPicker(browser)      //<5>
        );
    }

    public void pick(Month month, int day, int year) {           //<6>
        datepicker.pick(month, day, year);
    }

    public String getDate() {                                  //<7>
        return browser.getInputText(DATE_FIELD);
    }
}
