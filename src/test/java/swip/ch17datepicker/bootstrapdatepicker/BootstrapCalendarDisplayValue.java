package swip.ch17datepicker.bootstrapdatepicker;


import swip.ch15pageflow.framework.Browser;

import java.util.function.Function;

import static swip.ch17datepicker.bootstrapdatepicker.BootstrapByClassName.DISPLAY_MONTH_YEAR;
import static swip.ch17datepicker.datepicker.StringToMonth.TO_MONTH;

public enum BootstrapCalendarDisplayValue implements Function<Browser, Integer> {

    /**
     * Locate the integer value representing displayed year on a calendar
     */
    YEAR {
        public Integer apply(Browser browser) {
            String text = extractValue(browser, 1);    //<2>
            return Integer.parseInt(text);
        }
    },

    /**
     * Locate the integer value representing displayed month on a calendar
     */
    MONTH {
        public Integer apply(Browser browser) {
            String text = extractValue(browser, 0);   //<3>
            return TO_MONTH.apply(text).ordinal();
        }
    };

    private static String extractValue(Browser browser, int position) {        //<1>
        return browser.untilFound(BootstrapByClassName.CALENDAR)
            .untilFound(DISPLAY_MONTH_YEAR)
            .getText().split(" ")[position];
    }

}
