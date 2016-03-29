package swip.ch17datepicker.jquerydatepicker.v3;

import swip.ch15pageflow.v2.framework.Browser;

import java.util.function.Consumer;
import java.util.function.Function;


public class MonthPicker {

    private final Browser browser;
    private final Consumer<Browser> previousMonth;
    private final Consumer<Browser> nextMonth;
    private final Function<Browser, Integer> displayMonth;


    public MonthPicker(Browser browser,
                       Consumer<Browser> previousMonth,
                       Consumer<Browser> nextMonth,
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
                nextMonth.accept(browser);
            }
        } else if (difference > 0) {
            for (int i = 0; i < difference; i++) {
               previousMonth.accept(browser);
            }
        }
    }


}
