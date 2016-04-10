package swip.ch18datepicker.jquery.v4;

import swip.ch15pageflow.framework.Browser;

import java.time.Month;

import static swip.ch17jquerydatepicker.locators.JQueryByClassName.*;
import static swip.ch17jquerydatepicker.locators.JQueryById.TRIGGER_BY;
import static swip.ch17jquerydatepicker.locators.JQueryById.CALENDAR;


public class JQueryDatepickerPage {

    private final Browser browser;

    private final Datepicker datepicker;

    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }

    public String getDate() {
        return browser.getInputText(TRIGGER_BY);
    }

    public JQueryDatepickerPage(Browser b) {
        this.browser = b;
        this.datepicker = new Datepicker(
            new Calendar(browser,
                browser -> browser.click(TRIGGER_BY)
            ),
            new YearPicker(browser,
                browser -> this.previousYear(),
                browser -> this.nextYear(),
                browser -> this.displayYear()
            ),
            new MonthPicker(browser,
                browser -> this.previousMonth(),
                browser -> this.nextMonth(),
                browser -> this.displayMonth()
            ), new JQueryDayPicker(browser));
    }

    private int displayMonth() {
        String text = browser.untilFound(CALENDAR).getUpperText(MONTH);
        return Month.valueOf(text).ordinal();
    }

    private int displayYear() {
        String text = browser.untilFound(CALENDAR).getText(YEAR);
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
        browser.untilFound(CALENDAR).click(PREV_MONTH_BUTTON);  //<3>
    }

    private void nextMonth() {
        browser.untilFound(CALENDAR).click(NEXT_MONTH_BUTTON);  //<4>
    }
}
