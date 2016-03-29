package swip.ch18datepicker.jquerydatepicker.v1;


import swip.ch15pageflow.v2.framework.Browser;

import java.time.Month;

import static swip.ch17datepicker.JQueryByClassName.*;
import static swip.ch17datepicker.JQueryById.DATE_FIELD;
import static swip.ch17datepicker.JQueryById.CALENDAR;


public class JQueryDatepickerPage {

    private final Browser browser;    //<1>

    private final Datepicker datepicker;   //<2>

    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }  //<9>

    public String getDate() {
        return browser.getInputText(DATE_FIELD);
    }       //<10>

    public JQueryDatepickerPage(Browser b) {   //<3>
        this.browser = b;
        this.datepicker = new Datepicker(  //<4>
            new Calendar(browser,
                (Browser browser) -> {
                    browser.click(DATE_FIELD);
                }
            ),
            new YearPicker(browser,
                (Browser browser) -> {
                    for (int i = 0; i < 12; i++) {
                        previousMonth();
                    }
                },
                (Browser browser) -> {
                    for (int i = 0; i < 12; i++) {
                        nextMonth();
                    }
                },
                (Browser browser) -> {
                    String text = browser.untilFound(CALENDAR)
                        .getText(DISPLAY_YEAR);
                    return Integer.parseInt(text);
                }

            ),
            new MonthPicker(browser,
                (Browser browser) -> {
                    previousMonth();
                },
                (Browser browser) -> {
                    nextMonth();
                },
                (Browser browser) -> {
                    String text = browser.untilFound(CALENDAR)
                        .getText(DISPLAY_MONTH)
                        .toUpperCase();
                    return Month.valueOf(text).ordinal();
                }
            ), new JQueryDayPicker(browser));
    }

    private void previousMonth() {
        browser.findElement(CALENDAR)
            .click(PREV_MONTH_BUTTON);  //<3>
    }

    private void nextMonth() {
        browser.findElement(CALENDAR)
            .click(NEXT_MONTH_BUTTON);  //<4>
    }

}
