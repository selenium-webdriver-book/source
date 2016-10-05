package swb.ch18datepicker.jsdatepick;

import swb.framework.Browser;

import java.util.function.Function;

import static java.lang.Integer.parseInt;
import static swb.locators.StringToMonth.TO_MONTH;
import static swb.locators.jsdatepick.JsDatepickByClassName.CALENDAR;
import static swb.locators.jsdatepick.JsDatepickByClassName.DISPLAY_MONTH_YEAR;

public enum JsDatepickDisplayValueLambda implements Function<Browser, Integer> {

    DISPLAY_YEAR(browser -> parseInt(extract(browser, 1).trim())),
    DISPLAY_MONTH(browser -> TO_MONTH.apply(extract(browser, 0)).ordinal());

    private Function<Browser, Integer> function;

    JsDatepickDisplayValueLambda(Function<Browser, Integer> function) {
        this.function = function;
    }

    @Override
    public Integer apply(Browser browser) {
        return function.apply(browser);       //<2>
    }

    private static String extract(Browser browser, int i) {  //<1>
        return browser.await(CALENDAR).getText(DISPLAY_MONTH_YEAR).split(",")[i];
    }

}
