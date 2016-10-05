package swb.ch17jquerydatepicker.jquery.v2;

import swb.framework.Browser;

import static swb.locators.jquery.JQueryById.TRIGGER_BY;

public class JQueryCalendar {

    private final Browser browser;

    public JQueryCalendar(Browser browser) {
        this.browser = browser;
    }

    public void show() {
        browser.click(TRIGGER_BY);
    }
}
