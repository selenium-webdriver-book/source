package swip.ch18datepicker.jquery.v3;

import swip.ch15pageflow.v2.framework.Browser;

import java.util.function.Consumer;


public class Calendar {

    private final Browser browser;        //<1>
    private final Consumer<Browser> trigger;   //<2>

    /**
     * Constructor of the Calendar.
     *
     * @param browser
     * @param trigger
     */
    public Calendar(Browser browser, Consumer<Browser> trigger) { //<6>
        this.browser = browser;
        this.trigger = trigger;
    }

    /**
     * Display the calendar
     */
    public void show() {  //<7>
        trigger.accept(browser);      //<9>
    }
}
