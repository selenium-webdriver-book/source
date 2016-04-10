package swip.ch18datepicker.jquery.v3;

import swip.ch15pageflow.framework.Browser;

import java.util.function.Consumer;

import static swip.ch17jquerydatepicker.locators.JQueryByClassName.PREV_MONTH_BUTTON;
import static swip.ch17jquerydatepicker.locators.JQueryById.CALENDAR;

public class PreviousMonth implements Consumer<Browser> {

    @Override
    public void accept(Browser browser) {
        browser.untilFound(CALENDAR).click(PREV_MONTH_BUTTON);
    }
}
