package swip.ch17datepicker.jquerydatepicker.v5;


import swip.ch15pageflow.framework.Browser;

import java.util.function.Function;

import static swip.ch17datepicker.datepicker.StringToMonth.TO_MONTH;
import static swip.ch17datepicker.jquerydatepicker.v5.JQueryByClassName.DISPLAY_YEAR;
import static swip.ch17datepicker.jquerydatepicker.v5.JQueryById.UI_DATEPICKER_DIV;

public enum JQueryCalendarDisplayValue implements Function<Browser, Integer> {

    /**
     * Locate the integer value representing displayed year on a calendar
     */
    YEAR {                     //<1>
        public Integer apply(Browser browser) {
            String text = browser.untilFound(UI_DATEPICKER_DIV)
                .untilFound(DISPLAY_YEAR)
                .getText();
            return Integer.parseInt(text);
        }
    },

    /**
     * Locate the integer value representing displayed month on a calendar
     */
    MONTH {                  //<2>

        public Integer apply(Browser browser) {
            String text = browser.untilFound(UI_DATEPICKER_DIV)
                .untilFound(JQueryByClassName.DISPLAY_MONTH)
                .getText();
            return TO_MONTH.apply(text).ordinal();
        }

    }
}
