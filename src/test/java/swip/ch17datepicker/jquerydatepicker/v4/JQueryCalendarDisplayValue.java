package swip.ch17datepicker.jquerydatepicker.v4;


import swip.ch15pageflow.v2.framework.Browser;

import java.time.Month;
import java.util.function.Function;

import static swip.ch17datepicker.jquerydatepicker.JQueryByClassName.DISPLAY_MONTH;
import static swip.ch17datepicker.jquerydatepicker.JQueryByClassName.DISPLAY_YEAR;
import static swip.ch17datepicker.jquerydatepicker.JQueryById.UI_DATEPICKER_DIV;

public enum JQueryCalendarDisplayValue implements Function<Browser, Integer> {

    /**
     * Locate the integer value representing displayed year on a calendar
     */
    YEAR {                     //<1>
        public Integer apply(Browser browser) {
            String text = browser.untilFound(UI_DATEPICKER_DIV)
                .getText(DISPLAY_YEAR);
            return Integer.parseInt(text);
        }
    },

    /**
     * Locate the integer value representing displayed month on a calendar
     */
    MONTH {                  //<2>
        public Integer apply(Browser browser) {
            return Month.valueOf(
                browser.untilFound(UI_DATEPICKER_DIV)
                    .getText(DISPLAY_MONTH)
                    .toUpperCase()
            ).ordinal();
        }

    }
}
