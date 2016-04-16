package swip.ch17reactdatepicker.react.v2;

import swip.framework.Browser;

import static java.lang.Integer.parseInt;
import static swip.locators.StringToMonth.TO_MONTH;
import static swip.locators.ReactByClassName.*;


public class ReactMonthPicker {

    private final Browser browser;

    public ReactMonthPicker(Browser browser) {
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
        browser.untilFound(CALENDAR).click(PREV_MONTH_BUTTON);  //<3>
    }

    public void nextMonth() {
        browser.untilFound(CALENDAR).click(NEXT_MONTH_BUTTON);  //<4>
    }

    private int displayMonth() {
        return TO_MONTH.apply(extract(browser, 0)).ordinal();       //<1>
    }


    public String extract(Browser browser, int i) {  //<1>
        return browser.untilFound(CALENDAR).getText(DISPLAY_MONTH_YEAR).split(" ")[i];
    }
}
