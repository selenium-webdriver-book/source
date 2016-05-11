package swip.ch18datepicker.jquery.v3;

import swip.framework.Browser;

import java.util.function.Consumer;

import static swip.locators.jquery.JQueryByClassName.NEXT_MONTH_BUTTON;
import static swip.locators.jquery.JQueryById.CALENDAR;

public class NextMonth implements Consumer<Browser> {

    @Override
    public void accept(Browser browser) {
        browser.await(CALENDAR).click(NEXT_MONTH_BUTTON);
    }
}
