package swb.ch18datepicker.jsdatepick;

import swb.framework.Browser;

import java.util.function.Consumer;

import static swb.locators.jsdatepick.JsDatepickByClassName.*;
import static swb.locators.jsdatepick.JsDatepickById.TRIGGER_BY;

public enum JsDatepickControlsLambda implements Consumer<Browser> {    //<6>
    TRIGGER(browser -> browser.click(TRIGGER_BY)),
    NEXT_MONTH(browser -> browser.await(CALENDAR).click(NEXT_MONTH_BUTTON)),
    PREVIOUS_MONTH(browser -> browser.await(CALENDAR).click(PREV_MONTH_BUTTON)),
    NEXT_YEAR(browser -> browser.await(CALENDAR).click(NEXT_YEAR_BUTTON)),
    PREVIOUS_YEAR(browser -> browser.await(CALENDAR).click(PREV_YEAR_BUTTON));

    private Consumer<Browser> consumer;

    JsDatepickControlsLambda(Consumer<Browser> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void accept(Browser browser) {
        consumer.accept(browser);
    }
}