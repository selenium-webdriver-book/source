package swip.ch18datepicker.jquery.v2_5;

import swip.framework.Browser;

import java.util.function.Function;

import static swip.locators.JQueryById.TRIGGER_BY;

public class Trigger implements Function<Browser,Void> {

    @Override
    public Void apply(Browser browser) {
        browser.click(TRIGGER_BY);
        return null;
    }
}