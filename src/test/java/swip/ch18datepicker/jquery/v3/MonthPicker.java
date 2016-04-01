package swip.ch18datepicker.jquery.v3;

import swip.ch18datepicker.framework.Browser;

import java.util.function.Consumer;
import java.util.function.Function;


public class MonthPicker {

    private final Browser browser;                       //<1>
    private final Consumer<Browser> previousMonth;         //<2>
    private final Consumer<Browser> nextMonth;                //<3>
    private final Function<Browser, Integer> displayMonth;      //<4>


    public MonthPicker(Browser browser,                         //<5>
                       Consumer<Browser> previousMonth,
                       Consumer<Browser> nextMonth,
                       Function<Browser, Integer> displayMonth) {
        this.browser = browser;
        this.previousMonth = previousMonth;
        this.nextMonth = nextMonth;
        this.displayMonth = displayMonth;
    }

    public void pick(int month) {
        int difference =  displayMonth.apply(browser) - month;    //<6>
        if (difference < 0) {                             //<7>
            for (int i = difference; i < 0; i++) {
                nextMonth.accept(browser);            //<8>
            }
        } else if (difference > 0) {                       //<9>
            for (int i = 0; i < difference; i++) {              //<10>
               previousMonth.accept(browser);
            }
        }
    }
}
