package swip.ch17datepicker.bootstrapdatepicker;


import swip.ch15pageflow.framework.Browser;
import swip.ch17datepicker.datepicker.CalendarPicker;
import swip.ch17datepicker.datepicker.Datepicker;

import java.time.Month;

import static swip.ch17datepicker.bootstrapdatepicker.BootstrapCalendarControls.*;
import static swip.ch17datepicker.bootstrapdatepicker.BootstrapCalendarDisplayValue.MONTH;
import static swip.ch17datepicker.bootstrapdatepicker.BootstrapCalendarDisplayValue.YEAR;
import static swip.ch17datepicker.bootstrapdatepicker.ReactPredicates.REACT_CALENDAR_CLOSED;


public class BootstrapDatepickerPage {

    private final Browser browser;    //<1>

    private final Datepicker datepicker;   //<2>

    public BootstrapDatepickerPage(Browser browser) {   //<3>
        this.browser = browser;
        this.datepicker = new Datepicker(  //<4>
            browser,
            TRIGGER,                                                      //<5>
            new CalendarPicker(browser, PREVIOUS_YEAR, NEXT_YEAR, YEAR),     //<6>
            new CalendarPicker(browser, PREVIOUS_MONTH, NEXT_MONTH, MONTH),  //<7>
            new BootstrapDayPicker(browser, REACT_CALENDAR_CLOSED)      //<8>
        );
    }


    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }  //<9>

    public String getDate() {
        return browser.getInputText(BootstrapByClassName.TRIGGER);
    }       //<10>

}
