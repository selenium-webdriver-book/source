package swip.ch17datepicker.jquerydatepicker.v2;

import org.openqa.selenium.By;
import swip.ch15pageflow.framework.Browser;


public class JQueryYearControl {

    private final Browser browser;

    public JQueryYearControl(Browser browser) {
        this.browser = browser;
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
            previousMonth();
        }
    }

    private void nextYear() {
        for (int i = 0; i < 12; i++) {
            nextMonth();
        }
    }

    private void previousMonth() {
        browser.findElement(By.id("ui-datepicker-div"))
            .findElement(By.className("ui-datepicker-prev"))
            .click();  //<3>
    }

    private void nextMonth() {
        browser.findElement(By.id("ui-datepicker-div"))
            .findElement(By.className("ui-datepicker-next"))
            .click();  //<4>
    }

    private int displayedYear() {
        return Integer.parseInt(
            browser.findElement(By.id("ui-datepicker-div"))
                .findElement(By.className("ui-datepicker-year"))
                .getText()
        );
    }

}
