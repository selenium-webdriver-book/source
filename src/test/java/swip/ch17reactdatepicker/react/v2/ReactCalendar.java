package swip.ch17reactdatepicker.react.v2;

import swip.framework.Browser;

import static swip.locators.react.ReactByXpath.TRIGGER_BY;

public class ReactCalendar {

    private final Browser browser;

    public ReactCalendar(Browser browser) {
        this.browser = browser;
    }

    public void show() {
        browser.click(TRIGGER_BY);
    }
}
