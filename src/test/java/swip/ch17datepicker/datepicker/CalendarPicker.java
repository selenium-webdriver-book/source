package swip.ch17datepicker.datepicker;

import swip.ch15pageflow.framework.Browser;

import java.util.function.Function;

public class CalendarPicker {

    private final Browser browser;  //<1>
    private final Function<Browser, Void> previous;      //<2>
    private final Function<Browser, Void> next;          //<3>
    private final Function<Browser, Integer> displayedValue; //<4>

    public CalendarPicker(Browser browser,
                          Function<Browser, Void> previous,
                          Function<Browser, Void> next,
                          Function<Browser, Integer> displayedValue) {    //<5>
        this.browser = browser;
        this.previous = previous;
        this.next = next;
        this.displayedValue = displayedValue;
    }

    /**
     *
     * @param flipTo
     */
    void pick(int flipTo) {
        int difference = displayedValue.apply(browser) - flipTo;
        if (difference < 0) {
            for (int i = difference; i < 0; i++) {
                next.apply(browser);
            }
        } else if (difference > 0) {
            for (int i = 0; i < difference; i++) {
                previous.apply(browser);
            }
        }
    }
}
