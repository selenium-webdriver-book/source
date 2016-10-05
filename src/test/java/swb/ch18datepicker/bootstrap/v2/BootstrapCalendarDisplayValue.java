package swb.ch18datepicker.bootstrap.v2;

import swb.framework.Browser;
import swb.locators.StringToMonth;

import java.util.function.Function;

import static java.lang.Integer.parseInt;
import static swb.locators.bootstrap.BootstrapByClassName.CALENDAR;
import static swb.locators.bootstrap.BootstrapByClassName.DISPLAY_MONTH_YEAR;

public enum BootstrapCalendarDisplayValue implements Function<Browser, Integer> {

    /**
     * Locate the integer value representing displayed year on a calendar
     */
    DISPLAY_YEAR {
        @Override
        public Integer apply(Browser browser) {
            return parseInt(extract(browser, 1));       //<1>
        }
    },

    /**
     * Locate the integer value representing displayed month on a calendar
     */
    DISPLAY_MONTH {
        @Override
        public Integer apply(Browser browser) {
            return StringToMonth.TO_MONTH.apply(extract(browser, 0)).ordinal();       //<2>
        }
    };

    private static String extract(Browser browser, int i) {  //<1>
        return browser.await(CALENDAR).getText(DISPLAY_MONTH_YEAR).split(" ")[i];
    }

}
