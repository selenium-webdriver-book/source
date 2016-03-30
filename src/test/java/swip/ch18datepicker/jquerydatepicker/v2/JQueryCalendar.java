package swip.ch18datepicker.jquerydatepicker.v2;

import swip.ch15pageflow.v2.framework.Browser;

import static swip.ch18datepicker.jquerydatepicker.JQueryById.DATE_FIELD;


public class JQueryCalendar {

    private final Browser browser;

    public JQueryCalendar(Browser browser) {
        this.browser = browser;
    }

    public void show() {
        browser.click(DATE_FIELD);
    }
}
