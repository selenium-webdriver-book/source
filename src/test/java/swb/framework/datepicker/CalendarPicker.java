package swb.framework.datepicker;

import swb.framework.Browser;

import java.util.function.Consumer;
import java.util.function.Function;

public class CalendarPicker {

    private final Browser browser;  //<1>
    private final Consumer<Browser> previous;      //<2>
    private final Consumer<Browser> next;          //<3>
    private final Function<Browser, Integer> displayValue; //<4>

    public CalendarPicker(Browser browser,
                          Consumer<Browser> previous,
                          Consumer<Browser> next,
                          Function<Browser, Integer> displayValue) {    //<5>
        this.browser = browser;
        this.previous = previous;
        this.next = next;
        this.displayValue = displayValue;
    }

    /**
     * @param value the year or month to pick
     */
    void pick(int value) {
        int difference;
        while ((difference = displayValue.apply(browser) - value) != 0) {
            if (difference < 0) {                                 //<7>
                for (int i = difference; i < 0; i++) {       //<8>
                    next.accept(browser);
                }
            } else if (difference > 0) {         //<9>
                for (int i = 0; i < difference; i++) {   //<10>
                    previous.accept(browser);
                }
            }
            if (difference == displayValue.apply(browser) - value) {
                break;
            }
        }

    }
}
