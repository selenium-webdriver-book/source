package swip.ch18datepicker.jquery.v3;

import swip.ch18datepicker.framework.Browser;

import java.util.function.Function;

import static swip.ch17jquerydatepicker.JQueryByClassName.DISPLAY_YEAR;
import static swip.ch17jquerydatepicker.JQueryById.CALENDAR;

public class DisplayYear implements Function<Browser, Integer> {
    @Override
    public Integer apply(Browser browser) {
        String text = browser.untilFound(CALENDAR)
            .getText(DISPLAY_YEAR);
        return Integer.parseInt(text);
    }
}
