package swip.ch17datepicker.jquerydatepicker;


import swip.ch15pageflow.framework.Browser;
import swip.ch17datepicker.datepicker.CalendarPicker;
import swip.ch17datepicker.datepicker.Datepicker;

import java.time.Month;

import static swip.ch17datepicker.PagePredicates.JQUERY_CALENDAR_NOT_DISPLAYED;
import static swip.ch17datepicker.jquerydatepicker.CalendarControls.*;
import static swip.ch17datepicker.jquerydatepicker.CalendarDisplayValue.MONTH;
import static swip.ch17datepicker.jquerydatepicker.CalendarDisplayValue.YEAR;
import static swip.ch17datepicker.jquerydatepicker.Id.DATE_PICKER;


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
            new JQueryDayPicker(browser, JQUERY_CALENDAR_NOT_DISPLAYED)      //<8>
        );
    }


    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }  //<9>

    public String getDate() {
        return browser.getInputText(DATE_PICKER);
    }       //<10>

}
