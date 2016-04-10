package swip.ch18datepicker.jquery.v4;

import swip.ch15pageflow.framework.Browser;

import java.util.function.Consumer;

public class Calendar {

    private final Browser browser;        //<1>
    private final Consumer<Browser> trigger;   //<2>

    public Calendar(Browser browser, Consumer<Browser> trigger) { //<6>
        this.browser = browser;
        this.trigger = trigger;
    }

    public void show() {  //<7>
        trigger.accept(browser);      //<9>
    }
}
