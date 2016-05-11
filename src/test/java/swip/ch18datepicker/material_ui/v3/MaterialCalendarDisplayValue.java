package swip.ch18datepicker.material_ui.v3;

import swip.framework.Browser;
import swip.framework.SearchScope;

import java.util.function.Function;

import static java.lang.Integer.parseInt;
import static swip.locators.StringToMonth.TO_MONTH;
import static swip.locators.material_ui.MaterialByXpath.DISPLAY_MONTH_YEAR;

public enum MaterialCalendarDisplayValue implements Function<Browser, Integer> {

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
            return TO_MONTH.apply(extract(browser, 0)).ordinal();       //<2>
        }
    };

    private static String extract(Browser browser, int i) {  //<1>
        return browser.await(
            (SearchScope s) ->
                browser.getText(DISPLAY_MONTH_YEAR).split(" ")[i]);
    }

}
