package swip.ch18.framework.v4;

import swip.ch18.framework.Browser;
import swip.ch18.framework.datepicker.DayPicker;

import static org.openqa.selenium.By.linkText;
import static swip.ch18.framework.v4.JQueryById.UI_DATEPICKER_DIV;

public class JQueryDayPicker implements DayPicker {

    private Browser browser;                       //<1>

    public JQueryDayPicker(Browser browser) { //<2>
        this.browser = browser;
    }

    public void pick(int day) {
        browser.untilFound(UI_DATEPICKER_DIV)
            .click(() -> linkText(String.valueOf(day))); //<9>
        browser.until(JQueryPredicates.CALENDAR_CLOSED);  //<11>
    }
}
