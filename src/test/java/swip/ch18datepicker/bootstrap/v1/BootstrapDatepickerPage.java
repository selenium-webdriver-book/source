package swip.ch18datepicker.bootstrap.v1;

import swip.framework.Browser;
import swip.framework.datepicker.Calendar;
import swip.framework.datepicker.CalendarPicker;
import swip.framework.datepicker.Datepicker;
import swip.locators.StringToMonth;

import java.time.Month;

import static java.lang.Integer.parseInt;
import static swip.locators.bootstrap.BootstrapByClassName.*;

public class BootstrapDatepickerPage {

    private final Browser browser;
    private final Datepicker datepicker;

    public BootstrapDatepickerPage(Browser browser) {
        this.browser = browser;
        this.datepicker = new Datepicker(
            new Calendar(browser,
                b -> browser.click(TRIGGER_BY)
            ),
            new CalendarPicker(browser,
                b -> previousYear(),
                b -> nextYear(),
                b -> displayYear()
            ),
            new CalendarPicker(browser,
                b -> previousMonth(),
                b -> nextMonth(),
                b -> displayMonth()
            ), new BootstrapDayPicker(browser));
    }

    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }

    public String getDate() {
        return browser.getInputText(TRIGGER_BY);
    }

    private int displayMonth() {
        return StringToMonth.TO_MONTH.apply(extract(browser, 0)).ordinal();
    }

    private int displayYear() {
        return parseInt(extract(browser, 1));       //<1>
    }

    private void nextYear() {
        for (int i = 0; i < 12; i++) {
            nextMonth();
        }
    }

    private void previousYear() {
        for (int i = 0; i < 12; i++) {
            previousMonth();
        }
    }

    private void previousMonth() {
        browser.untilFound(CALENDAR).click(PREV_MONTH_BUTTON);  //<3>
    }

    private void nextMonth() {
        browser.untilFound(CALENDAR).click(NEXT_MONTH_BUTTON);  //<4>
    }

    private static String extract(Browser browser, int i) {  //<1>
        return browser.untilFound(CALENDAR).getText(DISPLAY_MONTH_YEAR).split(" ")[i];
    }
}
