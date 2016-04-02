package swip.ch17jquerydatepicker.v2;


import swip.ch17jquerydatepicker.framework.Browser;

import static swip.ch17jquerydatepicker.JQueryByClassName.*;
import static swip.ch17jquerydatepicker.JQueryById.CALENDAR;


public class JQueryYearPicker {

    private final Browser browser;
    private final JQueryMonthPicker monthPicker;

    public JQueryYearPicker(Browser browser) {
        this.browser = browser;
        this.monthPicker = new JQueryMonthPicker(browser);
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
            monthPicker.previousMonth();
        }
    }

    private void nextYear() {
        for (int i = 0; i < 12; i++) {
            monthPicker.nextMonth();
        }
    }

    private int displayedYear() {
        String text = browser.untilFound(CALENDAR).getText(YEAR);
        return Integer.parseInt(text);
    }

}
