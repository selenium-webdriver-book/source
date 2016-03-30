package swip.ch18datepicker.jquerydatepicker.v1.v1_2;

import swip.ch15pageflow.v2.framework.Browser;

import static swip.ch18datepicker.jquerydatepicker.JQueryByClassName.*;
import static swip.ch18datepicker.jquerydatepicker.JQueryById.UI_DATEPICKER_DIV;


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
        return Integer.parseInt(
            browser.untilFound(UI_DATEPICKER_DIV)
                .getText(DISPLAY_YEAR)
        );
    }

    private void previousMonth() {
        browser.untilFound(UI_DATEPICKER_DIV)
            .click(PREV_MONTH_BUTTON);  //<3>
    }

    private void nextMonth() {
        browser.untilFound(UI_DATEPICKER_DIV)
            .click(NEXT_MONTH_BUTTON);  //<4>
    }

}
