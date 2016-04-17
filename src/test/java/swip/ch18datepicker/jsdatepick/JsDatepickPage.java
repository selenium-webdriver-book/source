package swip.ch18datepicker.jsdatepick;

import swip.framework.Browser;
import swip.framework.datepicker.Calendar;
import swip.framework.datepicker.CalendarPicker;
import swip.framework.datepicker.Datepicker;

import java.time.Month;

import static swip.ch18datepicker.jsdatepick.JsDatepickControls.*;
import static swip.ch18datepicker.jsdatepick.JsDatepickDisplayValue.DISPLAY_MONTH;
import static swip.ch18datepicker.jsdatepick.JsDatepickDisplayValue.DISPLAY_YEAR;
import static swip.locators.jsdatepick.JsDatepickById.TRIGGER_BY;

public class JsDatepickPage {

    private final Browser browser;
    private final Datepicker datepicker;

    public JsDatepickPage(Browser browser) {
        this.browser = browser;
        this.datepicker = new Datepicker(
            new Calendar(browser, TRIGGER),
            new CalendarPicker(browser, PREVIOUS_YEAR, NEXT_YEAR, DISPLAY_YEAR),
            new CalendarPicker(browser, PREVIOUS_MONTH, NEXT_MONTH, DISPLAY_MONTH),
            new JsDayPicker(browser)
        );
    }

    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }

    public String getDate() {
        return browser.getInputText(TRIGGER_BY);
    }

}
