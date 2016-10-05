package swb.ch18datepicker.material_ui.v2;

import swb.ch18datepicker.datepicker.v1.Calendar;
import swb.ch18datepicker.datepicker.v2.CalendarPicker;
import swb.ch18datepicker.datepicker.v2.Datepicker;
import swb.ch18datepicker.material_ui.v1.MaterialCalendarControls;
import swb.ch18datepicker.material_ui.v1.MaterialDayPicker;
import swb.framework.Browser;

import java.time.Month;

import static swb.ch18datepicker.material_ui.v1.MaterialCalendarControls.*;
import static swb.ch18datepicker.material_ui.v1.MaterialCalendarDisplayValue.DISPLAY_MONTH;
import static swb.ch18datepicker.material_ui.v1.MaterialCalendarDisplayValue.DISPLAY_YEAR;
import static swb.locators.material_ui.MaterialByXpath.TRIGGER_BY;

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
