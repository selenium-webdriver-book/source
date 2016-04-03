package swip.ch18datepicker.jquery.v5;


import swip.ch15pageflow.v2.framework.Browser;

import java.time.Month;
import java.util.function.Function;

import static swip.ch17jquerydatepicker.JQueryByClassName.MONTH;
import static swip.ch17jquerydatepicker.JQueryByClassName.YEAR;
import static swip.ch17jquerydatepicker.JQueryById.CALENDAR;



public enum JQueryCalendarDisplayValue implements Function<Browser, Integer> {

    /**
     * Locate the integer value representing displayed year on a calendar
     */
    DISPLAY_YEAR {
        public Integer apply(Browser browser) {
            String text = browser.untilFound(CALENDAR)
                .getText(YEAR);
            return Integer.parseInt(text);
        }
    },               //<1>

    /**
     * Locate the integer value representing displayed month on a calendar
     */
    DISPLAY_MONTH {
        public Integer apply(Browser browser) {
            String text = browser.untilFound(CALENDAR)
                .getText(MONTH).toUpperCase();
            return Month.valueOf(text).ordinal();
        }
    }             //<2>
}
