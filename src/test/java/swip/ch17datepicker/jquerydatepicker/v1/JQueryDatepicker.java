package swip.ch17datepicker.jquerydatepicker.v1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import swip.ch15pageflow.framework.Browser;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;


public class JQueryDatepicker {

    private Browser browser;

    public JQueryDatepicker(Browser browser) {
        this.browser = browser;
    }

    public void pick(Month month, int day, int year) {
        LocalDate.of(year, month.ordinal() + 1, day);
        show();
        pickYear(year);
        pickMonth(month.ordinal());
        pickDay(day);
    }

    public String getDate() {
        return browser.findElement(By.id("datepicker")).getAttribute("value");
    }

    private void show() {
        WebElement element = browser.findElement(By.id("datepicker"));
        element.click();
    }

    private void pickYear(int year) {
        int difference =  displayedYear() - year;
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

    private void pickMonth(int month) {
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

    private void pickDay(int day) {
        List<WebElement> tds = calendar().findElements(By.tagName("td"));
        for (WebElement td : tds) {
            if (td.getText().equals(String.valueOf(day))) {
                td.click();
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
        calendar().findElement(By.className("ui-datepicker-prev")).click();  //<3>
    }

    private void nextMonth() {
        calendar().findElement(By.className("ui-datepicker-next")).click();  //<4>
    }

    private WebElement calendar() {
        return browser.findElement(By.id("ui-datepicker-div"));   //<5>
    }

    private int displayedYear() {
        return Integer.parseInt(
            calendar().findElement(By.className("ui-datepicker-year")).getText()
        );
    }


    private int displayedMonth() {
        return Month.valueOf(
            calendar().findElement(By.className("ui-datepicker-month"))
                .getText()
                .toUpperCase()
        ).ordinal();   //<7>
    }
}
