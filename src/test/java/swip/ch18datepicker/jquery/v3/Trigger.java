package swip.ch18datepicker.jquery.v3;

import swip.ch15pageflow.framework.Browser;

import java.util.function.Consumer;

import static swip.ch17jquerydatepicker.locators.JQueryById.TRIGGER_BY;

public class Trigger implements Consumer<Browser> {

    @Override
    public void accept(Browser browser) {
        browser.click(TRIGGER_BY);
    }
}