package swip.ch17datepicker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import swip.ch14elements.framework.Browser;

import java.time.Month;
import java.util.Date;
import java.util.List;


public class MuchBetterJQueryDatepicker {

    private Browser browser;

    public MuchBetterJQueryDatepicker(Browser browser) {
        this.browser = browser;
    }


    public String pickDate(Date date) {

        browser.get("/date-picker.html");

        show();
        pickYear(date.getYear() + 1900);
        pickMonth(date.getMonth());
        pickDay(date.getDay() + 1);

        return  browser.findElement(By.id("datepicker")).getAttribute("value");
    }

    private void show() {
        WebElement element = browser.findElement(By.id("datepicker"));
        element.click();
    }

    private void pickYear(int year) {
        if (displayedYear() < year) {        //<1>
            while (displayedYear() != year) {
                nextMonth();
            }
        } else if (displayedYear() > year) {
            while (displayedYear() != year) {
                previousMonth();
            }
        }
    }

    private void pickMonth(int month) {
        if (displayedMonth() < month) {             //<2>
            while (displayedMonth() != month) {
                nextMonth();
            }
        } else if (displayedMonth() > month) {
            while (displayedMonth() != month) {
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
        );   //<6>
    }


    private int displayedMonth() {
        return Month.valueOf(
            calendar().findElement(By.className("ui-datepicker-month"))
                .getText()
                .toUpperCase()
        ).ordinal();   //<7>
    }
}
