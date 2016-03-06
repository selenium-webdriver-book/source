package swip.ch17datepicker.jquerydatepicker.v4;

import swip.ch15pageflow.v2.framework.Browser;

import java.util.function.Consumer;
import java.util.function.Function;


public class YearPicker {

    private final Browser browser;                             //<1>
    private final Consumer<Browser> previousYear;        //<2>
    private final Consumer<Browser> nextYear;            //<3>
    private final Function<Browser, Integer> displayYear;      //<4>

    public YearPicker(Browser browser,
                      Consumer<Browser> previousYear,
                      Consumer<Browser> nextYear,
                      Function<Browser, Integer> displayYear) {
        this.browser = browser;
        this.previousYear = previousYear;
        this.nextYear = nextYear;
        this.displayYear = displayYear;
    }  //<5>

    public void pickYear(int year) {
        int difference =  displayYear.apply(browser) - year;   //<6>
        if (difference < 0) {                                 //<7>
            for (int i = difference; i < 0; i++) {            //<8>
                nextYear.accept(browser);
            }
        } else if (difference > 0) {                  //<9>
            for (int i = 0; i < difference; i++) {     //<10>
                previousYear.accept(browser);
            }
        }
    }
}
