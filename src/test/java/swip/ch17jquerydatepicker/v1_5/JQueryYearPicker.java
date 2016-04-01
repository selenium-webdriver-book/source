package swip.ch17jquerydatepicker.v1_5;

import swip.ch17jquerydatepicker.framework.Browser;

import static swip.ch17jquerydatepicker.JQueryByClassName.*;
import static swip.ch17jquerydatepicker.JQueryById.CALENDAR;


public class JQueryYearPicker {

    private final Browser browser;

    public JQueryYearPicker(Browser browser) {
        this.browser = browser;
    }

    public void pickYear(int year) {
        int difference = displayedYear() - year;
        if (difference < 0) {
            for (int i = difference; i < 0; i++) {
                nextYear();
            }
        } else if (difference > 0) {
            for (int i = 0; i < difference; i++) {
                previousYear();
            }
        }
    }

    private void previousYear() {
        for (int i = 0; i < 12; i++) {
            previousMonth();
        }
    }

    private void nextYear() {
        for (int i = 0; i < 12; i++) {
            nextMonth();
        }
    }

    private int displayedYear() {
        String text = browser.untilFound(CALENDAR)
            .getText(DISPLAY_YEAR);
        return Integer.parseInt(text);
    }

    private void previousMonth() {
        browser.untilFound(CALENDAR).click(PREV_MONTH_BUTTON);  //<3>
    }

    private void nextMonth() {
        browser.untilFound(CALENDAR).click(NEXT_MONTH_BUTTON);  //<4>
    }

}
