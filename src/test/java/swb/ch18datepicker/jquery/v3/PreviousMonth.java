package swb.ch18datepicker.jquery.v3;

import swb.framework.Browser;

import java.util.function.Consumer;

import static swb.locators.jquery.JQueryByClassName.PREV_MONTH_BUTTON;
import static swb.locators.jquery.JQueryById.CALENDAR;

public class PreviousMonth implements Consumer<Browser> {

    @Override
    public void accept(Browser browser) {
        browser.await(CALENDAR).click(PREV_MONTH_BUTTON);
    }
}
