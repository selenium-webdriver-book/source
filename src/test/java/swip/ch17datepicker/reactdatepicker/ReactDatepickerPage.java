package swip.ch17datepicker.reactdatepicker;


import swip.ch15pageflow.framework.Browser;
import swip.ch17datepicker.datepicker.CalendarPicker;
import swip.ch17datepicker.datepicker.Datepicker;

import java.time.Month;

import static swip.ch17datepicker.reactdatepicker.ReactCalendarControls.*;
import static swip.ch17datepicker.reactdatepicker.ReactCalendarDisplayValue.MONTH;
import static swip.ch17datepicker.reactdatepicker.ReactCalendarDisplayValue.YEAR;
import static swip.ch17datepicker.reactdatepicker.ReactPredicates.REACT_CALENDAR_CLOSED;


public class ReactDatepickerPage {

    private final Browser browser;    //<1>

    private final Datepicker datepicker;   //<2>

    public ReactDatepickerPage(Browser browser) {   //<3>
        this.browser = browser;
        this.datepicker = new Datepicker(  //<4>
            browser,
            TRIGGER,                                                      //<5>
            new CalendarPicker(browser, PREVIOUS_YEAR, NEXT_YEAR, YEAR),     //<6>
            new CalendarPicker(browser, PREVIOUS_MONTH, NEXT_MONTH, MONTH),  //<7>
            new ReactDayPicker(browser, REACT_CALENDAR_CLOSED)      //<8>
        );
    }


    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }  //<9>

    public String getDate() {
        return browser.getInputText(ReactClassName.TRIGGER);
    }       //<10>

}
