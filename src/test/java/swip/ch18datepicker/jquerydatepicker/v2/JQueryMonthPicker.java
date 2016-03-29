package swip.ch18datepicker.jquerydatepicker.v2;

import swip.ch15pageflow.v2.framework.Browser;

import java.time.Month;

import static swip.ch17datepicker.jquerydatepicker.JQueryByClassName.*;
import static swip.ch17datepicker.jquerydatepicker.JQueryById.UI_DATEPICKER_DIV;


public class JQueryMonthPicker {

    private final Browser browser;


    public JQueryMonthPicker(Browser browser) {
        this.browser = browser;
    }

    public void pickMonth(int month) {
        int difference = displayedMonth() - month;
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

    public void previousMonth() {
        browser.untilFound(UI_DATEPICKER_DIV).click(PREV_MONTH_BUTTON);  //<3>
    }

    public void nextMonth() {
        browser.untilFound(UI_DATEPICKER_DIV).click(NEXT_MONTH_BUTTON);  //<4>
    }

    private int displayedMonth() {
        String text = browser.untilFound(UI_DATEPICKER_DIV)
            .getText(DISPLAY_MONTH).toUpperCase();
        return Month.valueOf(text).ordinal();   //<7>
    }
}
