package swip.ch17datepicker.datepicker;

import swip.ch15pageflow.framework.Browser;

import java.util.function.Function;

public class CalendarPicker {

    private final Browser browser;  //<1>
    private final Function<Browser, Void> previous;      //<2>
    private final Function<Browser, Void> next;          //<3>
    private final Function<Browser, Integer> displayValue; //<4>

    public CalendarPicker(Browser browser,
                          Function<Browser, Void> previous,
                          Function<Browser, Void> next,
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
        int difference = displayValue.apply(browser) - value;   //<6>
        if (difference < 0) {                                 //<7>
            for (int i = difference; i < 0; i++) {       //<8>
                next.apply(browser);
            }
        } else if (difference > 0) {         //<9>
            for (int i = 0; i < difference; i++) {   //<10>
                previous.apply(browser);
            }
        }
    }
}
