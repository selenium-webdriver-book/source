package swip.ch17datepicker.jquerydatepicker.v3;

import swip.ch15pageflow.v2.framework.Browser;

import java.time.Month;

import static swip.ch17datepicker.jquerydatepicker.v3.JQueryByClassName.*;
import static swip.ch17datepicker.jquerydatepicker.v3.JQueryById.UI_DATEPICKER_DIV;


public class JQueryMonthPicker {

    private final Browser browser;


    public JQueryMonthPicker(Browser browser) {
        this.browser = browser;
    }

    public void pickMonth(int month) {
        int difference =  displayedMonth() - month;
        if (difference < 0) {
            for (int i = difference; i < 0; i++) {
                nextMonth();
            }
        } else if (difference > 0) {
            for (int i = 0; i < difference; i++) {
               previousMonth();
            }
        }
    }

    private void previousMonth() {
        browser.untilFound(UI_DATEPICKER_DIV).untilFound(PREV_MONTH_BUTTON).click();  //<3>
    }

    private void nextMonth() {
        browser.untilFound(UI_DATEPICKER_DIV).untilFound(NEXT_MONTH_BUTTON).click();  //<4>
    }

    private int displayedMonth() {
        return Month.valueOf(
        browser.untilFound(UI_DATEPICKER_DIV).untilFound(DISPLAY_MONTH)
                .getText()
                .toUpperCase()
        ).ordinal();   //<7>
    }
}
