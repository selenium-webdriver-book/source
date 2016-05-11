package swip.ch17jquerydatepicker.jquery.v2;

import swip.framework.Browser;

import java.time.Month;

import static swip.locators.jquery.JQueryByClassName.*;
import static swip.locators.jquery.JQueryById.CALENDAR;

public class JQueryMonthPicker {

    private final Browser browser;

    public JQueryMonthPicker(Browser browser) {
        this.browser = browser;
    }

    public void pickMonth(int month) {
        int difference = displayMonth() - month;
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
        browser.await(CALENDAR).click(PREV_MONTH_BUTTON);  //<3>
    }

    public void nextMonth() {
        browser.await(CALENDAR).click(NEXT_MONTH_BUTTON);  //<4>
    }

    private int displayMonth() {
        String text = browser.await(CALENDAR).getUpperText(MONTH);
        return Month.valueOf(text).ordinal();   //<7>
    }
}
