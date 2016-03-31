package swip.ch18datepicker.jquery.v3;

import swip.ch18datepicker.framework.Browser;

import java.time.Month;
import java.util.function.Function;

import static swip.ch17jquerydatepicker.JQueryByClassName.DISPLAY_MONTH;
import static swip.ch17jquerydatepicker.JQueryById.UI_DATEPICKER_DIV;

/**
 * Created by yujunliang on 3/31/16.
 */
public class DisplayMonth implements Function<Browser, Integer> {

    @Override
    public Integer apply(Browser browser) {
        String text = browser.untilFound(UI_DATEPICKER_DIV)
            .getText(DISPLAY_MONTH).toUpperCase();
        return Month.valueOf(text).ordinal();
    }
}
