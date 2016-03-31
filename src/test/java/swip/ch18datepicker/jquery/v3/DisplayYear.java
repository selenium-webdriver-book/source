package swip.ch18datepicker.jquery.v3;

import swip.ch17jquerydatepicker.JQueryByClassName;
import swip.ch18datepicker.framework.Browser;

import java.util.function.Function;

import static swip.ch17jquerydatepicker.JQueryById.UI_DATEPICKER_DIV;

public class DisplayYear implements Function<Browser, Integer> {
    public Integer apply(Browser browser) {
        String text = browser.untilFound(UI_DATEPICKER_DIV)
            .getText(JQueryByClassName.DISPLAY_YEAR);
        return Integer.parseInt(text);
    }
}
