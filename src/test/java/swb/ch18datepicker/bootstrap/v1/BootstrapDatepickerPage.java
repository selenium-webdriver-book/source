package swb.ch18datepicker.bootstrap.v1;

import swb.framework.Browser;
import swb.framework.datepicker.Calendar;
import swb.framework.datepicker.CalendarPicker;
import swb.framework.datepicker.Datepicker;

import java.time.Month;

import static java.lang.Integer.parseInt;
import static swb.locators.StringToMonth.TO_MONTH;
import static swb.locators.bootstrap.BootstrapByClassName.*;

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
            ),
            new BootstrapDayPicker(browser));
    }

    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }

    public String getDate() {
        return browser.getInputText(TRIGGER_BY);
    }

    private int displayYear() {
        return parseInt(extract(1));       //<1>
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

    private String extract(int i) {  //<1>
        return browser.await(CALENDAR)
            .getText(DISPLAY_MONTH_YEAR).split(" ")[i];
    }

    private void previousMonth() {
        browser.await(CALENDAR).click(PREV_MONTH_BUTTON);  //<3>
    }

    private void nextMonth() {
        browser.await(CALENDAR).click(NEXT_MONTH_BUTTON);  //<4>
    }

    private int displayMonth() {
        return TO_MONTH.apply(extract(0)).ordinal();
    }

}
