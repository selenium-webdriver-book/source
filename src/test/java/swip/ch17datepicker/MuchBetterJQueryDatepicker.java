package swip.ch17datepicker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import swip.ch15pageflow.framework.Browser;


import java.time.Month;
import java.util.Date;
import java.util.List;


public class MuchBetterJQueryDatepicker {

    private Browser browser;

    public MuchBetterJQueryDatepicker(Browser browser) {
        this.browser = browser;
    }


    public String pickDate(Date date) {
        show();
        pickYear(date.getYear() + 1900);
        pickMonth(date.getMonth());
        pickDay(date.getDay() + 1);

        return browser.findElement(By.id("datepicker")).getAttribute("value");
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
        List<WebElement> tds = browser.findElement(By.id("ui-datepicker-div")).findElements(By.tagName("td"));
        for (WebElement td : tds) {
            if (td.getText().equals(String.valueOf(day))) {
                td.click();
            }
        }
    }

    private void previousMonth() {
        browser.findElement(By.id("ui-datepicker-div")).findElement(By.className("ui-datepicker-prev")).click();  //<3>
    }

    private void nextMonth() {
        browser.findElement(By.id("ui-datepicker-div")).findElement(By.className("ui-datepicker-next")).click();  //<4>
    }

    private int displayedYear() {
        return Integer.parseInt(
            browser.findElement(By.id("ui-datepicker-div")).findElement(By.className("ui-datepicker-year")).getText()
        );   //<5>
    }


    private int displayedMonth() {
        return Month.valueOf(
            browser.findElement(By.id("ui-datepicker-div")).findElement(By.className("ui-datepicker-month"))
                .getText()
                .toUpperCase()
        ).ordinal();   //<6>
    }
}
