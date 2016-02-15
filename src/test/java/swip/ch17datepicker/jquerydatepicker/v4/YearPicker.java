package swip.ch17datepicker.jquerydatepicker.v4;

import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.Element;

import java.util.function.Function;

import static swip.ch17datepicker.jquerydatepicker.v3.JQueryByClassName.*;
import static swip.ch17datepicker.jquerydatepicker.v3.JQueryById.UI_DATEPICKER_DIV;


public class YearPicker {

    private Browser browser;
    private final Function<Browser, Void> previousYear;
    private final Function<Browser, Void> nextYear;
    private final Function<Browser, Integer> displayYear;

    public YearPicker(Browser browser,
                      Function<Browser, Void> previousYear,
                      Function<Browser, Void> nextYear,
                      Function<Browser, Integer> displayYear) {    //<5>) {
        this.browser = browser;
        this.previousYear = previousYear;
        this.nextYear = nextYear;
        this.displayYear = displayYear;
    }

    public void pickYear(int year) {
        int difference =  displayYear.apply(browser) - year;
        if (difference < 0) {
            for (int i = difference; i < 0; i++) {
                nextYear.apply(browser);
            }
        } else if (difference > 0) {
            for (int i = 0; i < difference; i++) {
                previousYear.apply(browser);
            }
        }
    }

}
