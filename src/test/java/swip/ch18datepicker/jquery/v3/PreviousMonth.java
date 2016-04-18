package swip.ch18datepicker.jquery.v3;

import swip.framework.Browser;

import java.util.function.Consumer;

import static swip.locators.jquery.JQueryByClassName.PREV_MONTH_BUTTON;
import static swip.locators.jquery.JQueryById.CALENDAR;

public class PreviousMonth implements Consumer<Browser> {

    @Override
    public void accept(Browser browser) {
        browser.untilFound(CALENDAR).click(PREV_MONTH_BUTTON);
    }
}
