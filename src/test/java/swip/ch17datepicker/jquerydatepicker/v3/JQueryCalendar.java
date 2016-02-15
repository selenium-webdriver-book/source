package swip.ch17datepicker.jquerydatepicker.v3;

import org.openqa.selenium.WebElement;
import swip.ch15pageflow.framework.Browser;

import static swip.ch17datepicker.jquerydatepicker.v3.JQueryById.DATE_FIELD;


public class JQueryCalendar {

    private Browser browser;

    public JQueryCalendar(Browser browser) {
        this.browser = browser;
    }

    public void show() {
        WebElement element = browser.findElement(DATE_FIELD);
        element.click();
    }
}
