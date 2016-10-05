package swb.ch17jquerydatepicker.jquery.v2;

import swb.framework.Browser;

import static swb.locators.jquery.JQueryByClassName.YEAR;
import static swb.locators.jquery.JQueryById.CALENDAR;

public class JQueryYearPicker {

    private final Browser browser;
    private final JQueryMonthPicker monthPicker;

    public JQueryYearPicker(Browser browser) {
        this.browser = browser;
        this.monthPicker = new JQueryMonthPicker(browser);
    }

    public void pickYear(int year) {
        int difference = displayYear() - year;
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

    private int displayYear() {
        String text = browser.await(CALENDAR).getText(YEAR);
        return Integer.parseInt(text);
    }

}
