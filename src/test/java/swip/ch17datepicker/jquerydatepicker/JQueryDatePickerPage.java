package swip.ch17datepicker.jquerydatepicker;


import swip.ch15pageflow.framework.Browser;
import swip.ch17datepicker.datepicker.CalendarPicker;
import swip.ch17datepicker.datepicker.Datepicker;

import java.time.Month;

import static swip.ch17datepicker.jquerydatepicker.JQueryById.DATE_FIELD;
import static swip.ch17datepicker.jquerydatepicker.JQueryCalendarControls.*;
import static swip.ch17datepicker.jquerydatepicker.JQueryCalendarDisplayValue.MONTH;
import static swip.ch17datepicker.jquerydatepicker.JQueryCalendarDisplayValue.YEAR;
import static swip.ch17datepicker.jquerydatepicker.JQueryPredicates.CALENDAR_CLOSED;


public class JQueryDatePickerPage {

    private final Browser browser;    //<1>

    private final Datepicker datepicker;   //<2>

    public JQueryDatePickerPage(Browser browser) {   //<3>
        this.browser = browser;
        this.datepicker = new Datepicker(  //<4>
            browser,
            TRIGGER,                                                      //<5>
            new CalendarPicker(browser, PREVIOUS_YEAR, NEXT_YEAR, YEAR),     //<6>
            new CalendarPicker(browser, PREVIOUS_MONTH, NEXT_MONTH, MONTH),  //<7>
            new JQueryDayPicker(browser, CALENDAR_CLOSED)      //<8>
        );
    }


    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }  //<9>

    public String getDate() {
        return browser.getInputText(DATE_FIELD);
    }       //<10>

}
