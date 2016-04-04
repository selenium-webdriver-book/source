package swip.ch17jquerydatepicker.jquery.v2;


import swip.ch15pageflow.framework.Browser;

import static swip.ch17jquerydatepicker.locators.JQueryById.DATE_FIELD;


public class JQueryCalendar {

    private final Browser browser;

    public JQueryCalendar(Browser browser) {
        this.browser = browser;
    }

    public void show() {
        browser.click(DATE_FIELD);
    }
}
