package swip.ch17datepicker.jquerydatepicker;


import swip.ch15pageflow.framework.Browser;

import java.util.function.Function;

import static swip.ch17datepicker.datepicker.StringToMonth.TO_MONTH;
import static swip.ch17datepicker.jquerydatepicker.JQueyByClassName.UI_DATEPICKER_YEAR;
import static swip.ch17datepicker.jquerydatepicker.JQueryById.UI_DATEPICKER_DIV;

public enum JQueryCalendarDisplayValue implements Function<Browser, Integer> {

    /**
     * Locate the integer value representing displayed year on a calendar
     */
    YEAR {
        public Integer apply(Browser browser) {
            String text = browser.untilFound(UI_DATEPICKER_DIV)
                .untilFound(UI_DATEPICKER_YEAR)
                .getText();
            return Integer.parseInt(text);
        }
    },

    /**
     * Locate the integer value representing displayed month on a calendar
     */
    MONTH {
        public Integer apply(Browser browser) {
            String text = browser.untilFound(UI_DATEPICKER_DIV)
                .untilFound(JQueyByClassName.UI_DATEPICKER_MONTH)
                .getText();
            return TO_MONTH.apply(text).ordinal();
        }

    }

}
