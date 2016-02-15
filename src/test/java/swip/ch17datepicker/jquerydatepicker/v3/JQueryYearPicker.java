package swip.ch17datepicker.jquerydatepicker.v3;

import swip.ch15pageflow.framework.Browser;

import static swip.ch17datepicker.jquerydatepicker.v3.JQueryByClassName.*;
import static swip.ch17datepicker.jquerydatepicker.v3.JQueryById.UI_DATEPICKER_DIV;


public class JQueryYearPicker {

    private Browser browser;

    public JQueryYearPicker(Browser browser) {
        this.browser = browser;
    }

    public void pickYear(int year) {
        int difference =  displayedYear() - year;
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

    private void previousMonth() {
        browser.untilFound(UI_DATEPICKER_DIV).untilFound(PREV_MONTH_BUTTON).click();  //<3>
    }

    private void nextMonth() {
        browser.untilFound(UI_DATEPICKER_DIV).untilFound(NEXT_MONTH_BUTTON).click();  //<4>
    }

    private int displayedYear() {
        return Integer.parseInt(
            browser.untilFound(UI_DATEPICKER_DIV).untilFound(DISPLAY_YEAR).getText()
        );
    }

}
