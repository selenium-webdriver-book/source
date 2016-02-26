package swip.ch17datepicker.jquerydatepicker.v4;


import swip.ch15pageflow.framework.Browser;

import java.time.Month;

import static swip.ch17datepicker.jquerydatepicker.v3.JQueryByClassName.*;
import static swip.ch17datepicker.jquerydatepicker.v3.JQueryById.DATE_FIELD;
import static swip.ch17datepicker.jquerydatepicker.v3.JQueryById.UI_DATEPICKER_DIV;


public class JQueryDatePickerPage {

    private final Browser browser;    //<1>

    private final Datepicker datepicker;   //<2>

    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }  //<9>

    public String getDate() {
        return browser.getInputText(DATE_FIELD);
    }       //<10>

    public JQueryDatePickerPage(Browser b) {   //<3>
        this.browser = b;
        this.datepicker = new Datepicker(  //<4>
            new Calendar(browser,
                (Browser browser) -> {
                    browser.untilFound(DATE_FIELD).click();
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
                (Browser browser) ->
                    Integer.parseInt(
                        browser.findElement(UI_DATEPICKER_DIV)
                            .findElement(DISPLAY_YEAR).getText()
                    )

            ),
            new MonthPicker(browser,
                (Browser browser) -> {
                    previousMonth();
                },
                (Browser browser) -> {
                    nextMonth();
                },
                (Browser browser) ->
                    Month.valueOf(
                        browser.untilFound(UI_DATEPICKER_DIV)
                            .untilFound(DISPLAY_MONTH)
                            .getText()
                            .toUpperCase()
                    ).ordinal()
            ), new JQueryDayPicker(browser));
    }

    private void previousMonth() {
        browser.findElement(UI_DATEPICKER_DIV)
            .findElement(PREV_MONTH_BUTTON).click();  //<3>
    }

    private void nextMonth() {
        browser.findElement(UI_DATEPICKER_DIV)
            .findElement(NEXT_MONTH_BUTTON).click();  //<4>
    }

}
