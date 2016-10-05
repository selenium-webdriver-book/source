package swb.ch18datepicker.jsdatepick;

import swb.framework.Browser;
import swb.framework.datepicker.Calendar;
import swb.framework.datepicker.CalendarPicker;
import swb.framework.datepicker.Datepicker;

import java.time.Month;

import static swb.ch18datepicker.jsdatepick.JsDatepickControlsLambda.*;
import static swb.ch18datepicker.jsdatepick.JsDatepickDisplayValueLambda.DISPLAY_MONTH;
import static swb.ch18datepicker.jsdatepick.JsDatepickDisplayValueLambda.DISPLAY_YEAR;
import static swb.locators.jsdatepick.JsDatepickById.TRIGGER_BY;

public class JsDatepickPageLambda {

    private final Browser browser;
    private final Datepicker datepicker;

    public JsDatepickPageLambda(Browser browser) {
        this.browser = browser;
        this.datepicker = new Datepicker(
            new Calendar(browser, TRIGGER),
            new CalendarPicker(browser, PREVIOUS_YEAR, NEXT_YEAR, DISPLAY_YEAR),
            new CalendarPicker(browser, PREVIOUS_MONTH, NEXT_MONTH, DISPLAY_MONTH),
            new JsDatepickDayPicker(browser)
        );
    }

    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }

    public String getDate() {
        return browser.getInputText(TRIGGER_BY);
    }

}
