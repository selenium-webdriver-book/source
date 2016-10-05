package swb.ch18datepicker.jquery.v4;

import swb.ch18datepicker.jquery.v3.JQueryDayPicker;
import swb.framework.Browser;
import swb.framework.datepicker.Calendar;
import swb.framework.datepicker.CalendarPicker;
import swb.framework.datepicker.Datepicker;

import java.time.Month;

import static swb.locators.jquery.JQueryByClassName.*;
import static swb.locators.jquery.JQueryById.CALENDAR;
import static swb.locators.jquery.JQueryById.TRIGGER_BY;

public class JQueryDatepickerPage {

    private final Browser browser;
    private final Datepicker datepicker;

    public JQueryDatepickerPage(Browser browser) {
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
            ), new JQueryDayPicker(browser));
    }

    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }

    public String getDate() {
        return browser.getInputText(TRIGGER_BY);
    }

    private int displayMonth() {
        String text = browser.await(CALENDAR).getUpperText(MONTH);
        return Month.valueOf(text).ordinal();
    }

    private int displayYear() {
        String text = browser.await(CALENDAR).getText(YEAR);
        return Integer.parseInt(text);
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
        browser.await(CALENDAR).click(PREV_MONTH_BUTTON);  //<3>
    }

    private void nextMonth() {
        browser.await(CALENDAR).click(NEXT_MONTH_BUTTON);  //<4>
    }
}
