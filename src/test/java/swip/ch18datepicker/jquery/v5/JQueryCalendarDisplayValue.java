package swip.ch18datepicker.jquery.v5;

import swip.framework.Browser;

import java.time.Month;
import java.util.function.Function;

import static swip.locators.jquery.JQueryByClassName.MONTH;
import static swip.locators.jquery.JQueryByClassName.YEAR;
import static swip.locators.jquery.JQueryById.CALENDAR;

public enum JQueryCalendarDisplayValue implements Function<Browser, Integer> {

    /**
     * Locate the integer value representing displayed year on a calendar
     */
    DISPLAY_YEAR {
        @Override
        public Integer apply(Browser browser) {
            String text = browser.await(CALENDAR).getText(YEAR);
            return Integer.parseInt(text);
        }
    },

    /**
     * Locate the integer value representing displayed month on a calendar
     */
    DISPLAY_MONTH {
        @Override
        public Integer apply(Browser browser) {
            String text = browser.await(CALENDAR).getUpperText(MONTH);
            return Month.valueOf(text).ordinal();
        }
    }
}
