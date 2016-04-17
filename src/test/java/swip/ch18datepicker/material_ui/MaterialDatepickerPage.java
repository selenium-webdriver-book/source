package swip.ch18datepicker.material_ui;

import swip.framework.Browser;
import swip.framework.datepicker.Calendar;
import swip.framework.datepicker.CalendarPicker;
import swip.framework.datepicker.Datepicker;

import java.time.Month;

import static swip.ch18datepicker.material_ui.MaterialCalendarControls.*;
import static swip.ch18datepicker.material_ui.MaterialCalendarDisplayValue.DISPLAY_MONTH;
import static swip.ch18datepicker.material_ui.MaterialCalendarDisplayValue.DISPLAY_YEAR;
import static swip.locators.material_ui.MaterialByXpath.TRIGGER_BY;


public class MaterialDatepickerPage {

    private final Browser browser;
    private final Datepicker datepicker;

    public MaterialDatepickerPage(Browser browser) {
        this.browser = browser;
        this.datepicker = new Datepicker(
            new Calendar(browser, MaterialCalendarControls.TRIGGER),
            new CalendarPicker(browser, PREVIOUS_YEAR, NEXT_YEAR, DISPLAY_YEAR),
            new CalendarPicker(browser, PREVIOUS_MONTH, NEXT_MONTH, DISPLAY_MONTH),
            new MaterialDayPicker(browser)
        );
    }

    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }

    public String getDate() {
        return browser.getInputText(TRIGGER_BY);
    }

}
