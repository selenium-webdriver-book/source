package swip.ch17datepicker.bootstrapdatepicker;


import swip.ch15pageflow.v2.framework.Browser;

import java.util.function.Function;

import static java.lang.Integer.parseInt;
import static swip.ch17datepicker.bootstrapdatepicker.BootstrapByClassName.CALENDAR;
import static swip.ch17datepicker.bootstrapdatepicker.BootstrapByClassName.DISPLAY_MONTH_YEAR;
import static swip.ch17datepicker.bootstrapdatepicker.StringToMonth.TO_MONTH;

public enum BootstrapCalendarDisplayValue implements Function<Browser, Integer> {

    /**
     * Locate the integer value representing displayed year on a calendar
     */
    YEAR {
        public Integer apply(Browser browser) {
            return parseInt(extract(browser, 1));
        }
    },

    /**
     * Locate the integer value representing displayed month on a calendar
     */
    MONTH {
        public Integer apply(Browser browser) {
            return TO_MONTH.apply(extract(browser, 0)).ordinal();
        }
    };

    private static String extract(Browser browser, int position) {  //<1>
        return browser.untilFound(CALENDAR)
            .getText(DISPLAY_MONTH_YEAR)
            .split(" ")[position];
    }

}
