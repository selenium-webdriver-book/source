package swip.ch17reactdatepicker.react.v2;

import swip.framework.Browser;

import static java.lang.Integer.parseInt;

public class ReactYearPicker {

    private final Browser browser;
    private final ReactMonthPicker monthPicker;

    public ReactYearPicker(Browser browser) {
        this.browser = browser;
        this.monthPicker = new ReactMonthPicker(browser);
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
        return parseInt(monthPicker.extract(browser, 1));       //<1>
    }

}
