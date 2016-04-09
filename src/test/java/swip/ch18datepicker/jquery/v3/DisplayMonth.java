package swip.ch18datepicker.jquery.v3;


import swip.ch15pageflow.framework.Browser;

import java.time.Month;
import java.util.function.Function;

import static swip.ch17jquerydatepicker.locators.JQueryByClassName.MONTH;
import static swip.ch17jquerydatepicker.locators.JQueryById.CALENDAR;

/**
 * Created by yujunliang on 3/31/16.
 */
public class DisplayMonth implements Function<Browser, Integer> {

    @Override
    public Integer apply(Browser browser) {
        String text = browser.untilFound(CALENDAR)
            .getText(MONTH).toUpperCase();
        return Month.valueOf(text).ordinal();
    }
}
