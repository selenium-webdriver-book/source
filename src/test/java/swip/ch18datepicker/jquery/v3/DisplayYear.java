package swip.ch18datepicker.jquery.v3;

import swip.framework.Browser;

import java.util.function.Function;

import static swip.locators.jquery.JQueryByClassName.YEAR;
import static swip.locators.jquery.JQueryById.CALENDAR;

public class DisplayYear implements Function<Browser, Integer> {

    @Override
    public Integer apply(Browser browser) {
        String text = browser.await(CALENDAR).getText(YEAR);
        return Integer.parseInt(text);
    }
}
