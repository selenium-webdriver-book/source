package swip.ch18datepicker.jquery.v4;

import swip.ch15pageflow.framework.Browser;

import java.time.Month;

import static swip.ch17jquerydatepicker.locators.JQueryByClassName.*;
import static swip.ch17jquerydatepicker.locators.JQueryById.CALENDAR;
import static swip.ch17jquerydatepicker.locators.JQueryById.TRIGGER_BY;

public class JQueryDatepickerPage {

    private final Browser browser;
    private final Datepicker datepicker;

    public JQueryDatepickerPage(Browser browser) {
        this.browser = browser;
        this.datepicker = new Datepicker(
            new Calendar(browser,
                b -> browser.click(TRIGGER_BY)
            ),
            new YearPicker(browser,
                b -> previousYear(),
                b -> nextYear(),
                b -> displayYear()
            ),
            new MonthPicker(browser,
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
