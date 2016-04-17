package swip.ch17reactdatepicker.react.v1_5;

import swip.framework.Browser;

import static java.lang.Integer.parseInt;
import static swip.locators.react.ReactByClassName.*;

public class ReactYearPicker {

    private final Browser browser;

    public ReactYearPicker(Browser browser) {
        this.browser = browser;
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
            previousMonth();
        }
    }

    private void nextYear() {
        for (int i = 0; i < 12; i++) {
            nextMonth();
        }
    }

    private int displayYear() {
        return parseInt(extract(browser, 1));       //<1>
    }

    private void previousMonth() {
        browser.untilFound(CALENDAR).click(PREV_MONTH_BUTTON);  //<3>
    }

    private void nextMonth() {
        browser.untilFound(CALENDAR).click(NEXT_MONTH_BUTTON);  //<4>
    }


    private static String extract(Browser browser, int i) {  //<1>
        return browser.untilFound(CALENDAR).getText(DISPLAY_MONTH_YEAR).split(" ")[i];
    }
}
