package swip.ch17datepicker.jquerydatepicker.v3;

import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.Element;

import java.time.Month;

import static swip.ch17datepicker.jquerydatepicker.v3.JQueryByClassName.*;
import static swip.ch17datepicker.jquerydatepicker.v3.JQueryById.UI_DATEPICKER_DIV;


public class JQueryMonthControl {

    private Browser browser;


    public JQueryMonthControl(Browser browser) {
        this.browser = browser;
    }

    public void pickMonth(int month) {
        int difference =  displayedMonth() - month;
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

    private void previousMonth() {
        calendar().findElement(PREV_MONTH_BUTTON).click();  //<3>
    }

    private void nextMonth() {
        calendar().findElement(NEXT_MONTH_BUTTON).click();  //<4>
    }

    private Element calendar() {
        return browser.findElement(UI_DATEPICKER_DIV);   //<5>
    }

    private int displayedMonth() {
        return Month.valueOf(
        calendar().findElement(DISPLAY_MONTH)
                .getText()
                .toUpperCase()
        ).ordinal();   //<7>
    }
}
