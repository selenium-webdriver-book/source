package swip.ch17jquerydatepicker.v2;

import swip.ch15pageflow.v2.framework.Browser;
import swip.ch17jquerydatepicker.JQueryById;


public class JQueryCalendar {

    private final Browser browser;

    public JQueryCalendar(Browser browser) {
        this.browser = browser;
    }

    public void show() {
        browser.click(JQueryById.DATE_FIELD);
    }
}
