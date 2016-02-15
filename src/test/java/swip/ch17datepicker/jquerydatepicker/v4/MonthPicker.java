package swip.ch17datepicker.jquerydatepicker.v4;

import swip.ch15pageflow.framework.Browser;

import java.util.function.Function;


public class MonthPicker {

    private Browser browser;
    private final Function<Browser, Void> previousMonth;
    private final Function<Browser, Void> nextMonth;
    private final Function<Browser, Integer> displayMonth;


    public MonthPicker(Browser browser,
                       Function<Browser, Void> previousMonth,
                       Function<Browser, Void> nextMonth,
                       Function<Browser, Integer> displayMonth) {    //<5>
        this.browser = browser;
        this.previousMonth = previousMonth;
        this.nextMonth = nextMonth;
        this.displayMonth = displayMonth;
    }

    public void pickMonth(int month) {
        int difference =  displayMonth.apply(browser) - month;
        if (difference < 0) {
            for (int i = difference; i < 0; i++) {
                nextMonth.apply(browser);
            }
        } else if (difference > 0) {
            for (int i = 0; i < difference; i++) {
               previousMonth.apply(browser);
            }
        }
    }


}
