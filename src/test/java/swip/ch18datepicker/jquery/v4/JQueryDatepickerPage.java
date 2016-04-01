package swip.ch18datepicker.jquery.v4;


import swip.ch18datepicker.framework.Browser;


import java.time.Month;

import static swip.ch17jquerydatepicker.JQueryByClassName.*;
import static swip.ch17jquerydatepicker.JQueryById.DATE_FIELD;
import static swip.ch17jquerydatepicker.JQueryById.CALENDAR;


public class JQueryDatepickerPage {

    private final Browser browser;

    private final Datepicker datepicker;

    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }

    public String getDate() {
        return browser.getInputText(DATE_FIELD);
    }

    public JQueryDatepickerPage(Browser b) {
        this.browser = b;
        this.datepicker = new Datepicker(
            new Calendar(browser,
                (Browser browser) -> browser.click(DATE_FIELD)
            ),
            new YearPicker(browser,
                (Browser browser) -> previousYear(),
                (Browser browser) -> nextYear(),
                (Browser browser) -> displayYear()
            ),
            new MonthPicker(browser,
                (Browser browser) -> previousMonth(),
                (Browser browser) -> nextMonth(),
                (Browser browser) -> displayMonth()
            ), new JQueryDayPicker(browser));
    }

    private int displayMonth() {
        String text = browser.untilFound(CALENDAR)
            .getText(DISPLAY_MONTH)
            .toUpperCase();
        return Month.valueOf(text).ordinal();
    }

    private int displayYear() {
        String text = browser.untilFound(CALENDAR)
            .getText(DISPLAY_YEAR);
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
        browser.findElement(CALENDAR).click(PREV_MONTH_BUTTON);  //<3>
    }

    private void nextMonth() {
        browser.findElement(CALENDAR).click(NEXT_MONTH_BUTTON);  //<4>
    }
}
