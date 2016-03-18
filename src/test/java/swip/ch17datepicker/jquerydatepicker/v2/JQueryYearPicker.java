package swip.ch17datepicker.jquerydatepicker.v2;

import org.openqa.selenium.By;
import swip.ch15pageflow.v2.framework.Browser;


public class JQueryYearPicker {

    private final Browser browser;
    private final JQueryMonthPicker monthPicker;

    public JQueryYearPicker(Browser browser) {
        this.browser = browser;
        this.monthPicker = new JQueryMonthPicker(browser);
    }

    public void pickYear(int year) {
        int difference = displayedYear() - year;
        if (difference < 0) {
            for (int i = difference; i < 0; i++) {
                nextYear();
            }
        } else if (difference > 0) {
            for (int i = 0; i < difference; i++) {
                previousYear();
            }
        }
    }

    private void previousYear() {
        for (int i = 0; i < 12; i++) {
            monthPicker.previousMonth();
        }
    }

    private void nextYear() {
        for (int i = 0; i < 12; i++) {
            monthPicker.nextMonth();
        }
    }

    private int displayedYear() {
        return Integer.parseInt(
            browser.findElement(By.id("ui-datepicker-div"))
                .findElement(By.className("ui-datepicker-year"))
                .getText()
        );
    }

}
