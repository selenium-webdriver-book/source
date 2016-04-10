package swip.ch18datepicker.jquery.v3;

import swip.ch15pageflow.framework.Browser;

import java.util.function.Function;

import static swip.ch17jquerydatepicker.locators.JQueryByClassName.YEAR;
import static swip.ch17jquerydatepicker.locators.JQueryById.CALENDAR;

public class DisplayYear implements Function<Browser, Integer> {

    @Override
    public Integer apply(Browser browser) {
        String text = browser.untilFound(CALENDAR).getText(YEAR);
        return Integer.parseInt(text);
    }
}
