package swip.ch17datepicker.jquerydatepicker.v3;

import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.Element;

import static swip.ch17datepicker.jquerydatepicker.v3.JQueryById.DATE_FIELD;


public class JQueryCalendar {

    private Browser browser;

    public JQueryCalendar(Browser browser) {
        this.browser = browser;
    }

    public void show() {
        Element element = browser.untilFound(DATE_FIELD);
        element.click();
    }
}
