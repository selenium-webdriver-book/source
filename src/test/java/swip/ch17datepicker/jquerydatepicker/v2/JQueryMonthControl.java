package swip.ch17datepicker.jquerydatepicker.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import swip.ch15pageflow.framework.Browser;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


public class JQueryMonthControl {

    private Browser browser;


    public JQueryMonthControl(Browser browser) {
        this.browser = browser;
    }

    public void pickMonth(int month) {
        int difference =  displayedMonth() - month;
        if (difference < 0) {
            for (int i = difference; i < 0; i++) {
                nextMonth();
            }
        } else if (difference > 0) {
            for (int i = 0; i < difference; i++) {
               previousMonth();
            }
        }
    }


    private void previousMonth() {
        calendar().findElement(By.className("ui-datepicker-prev")).click();  //<3>
    }

    private void nextMonth() {
        calendar().findElement(By.className("ui-datepicker-next")).click();  //<4>
    }

    private WebElement calendar() {
        return browser.findElement(By.id("ui-datepicker-div"));   //<5>
    }

    private int displayedMonth() {
        return Month.valueOf(
            calendar().findElement(By.className("ui-datepicker-month"))
                .getText()
                .toUpperCase()
        ).ordinal();   //<7>
    }
}
