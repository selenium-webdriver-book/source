package swb.ch18datepicker.jquery.v3;

import swb.framework.Browser;

import java.util.function.Consumer;

import static swb.locators.jquery.JQueryById.TRIGGER_BY;

public class Trigger implements Consumer<Browser> {

    @Override
    public void accept(Browser browser) {
        browser.click(TRIGGER_BY);
    }
}