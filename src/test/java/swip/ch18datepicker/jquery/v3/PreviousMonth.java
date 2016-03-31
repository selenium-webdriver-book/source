package swip.ch18datepicker.jquery.v3;

import swip.ch18datepicker.framework.Browser;

import java.util.function.Consumer;

import static swip.ch17jquerydatepicker.JQueryByClassName.PREV_MONTH_BUTTON;
import static swip.ch17jquerydatepicker.JQueryById.UI_DATEPICKER_DIV;


public class PreviousMonth implements Consumer<Browser> {

    @Override
    public void accept(Browser browser) {
        browser.untilFound(UI_DATEPICKER_DIV)
            .click(PREV_MONTH_BUTTON);
    }
}
