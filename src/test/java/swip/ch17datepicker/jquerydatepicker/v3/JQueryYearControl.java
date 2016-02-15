package swip.ch17datepicker.jquerydatepicker.v3;

import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.Element;

import static swip.ch17datepicker.jquerydatepicker.v3.JQueryByClassName.*;
import static swip.ch17datepicker.jquerydatepicker.v3.JQueryById.UI_DATEPICKER_DIV;


public class JQueryYearControl {

    private Browser browser;

    public JQueryYearControl(Browser browser) {
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
        calendar().findElement(PREV_MONTH_BUTTON).click();  //<3>
    }

    private void nextMonth() {
        calendar().findElement(NEXT_MONTH_BUTTON).click();  //<4>
    }

    private Element calendar() {
        return browser.findElement(UI_DATEPICKER_DIV);   //<5>
    }

    private int displayedYear() {
        return Integer.parseInt(
            calendar().findElement(DISPLAY_YEAR).getText()
        );
    }

}
