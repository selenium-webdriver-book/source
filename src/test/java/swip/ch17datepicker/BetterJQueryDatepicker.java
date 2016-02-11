package swip.ch17datepicker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import swip.ch15pageflow.framework.Browser;


import java.time.Month;
import java.util.Date;
import java.util.List;


public class BetterJQueryDatepicker {

    private Browser browser;
    private WebElement datepicker;

    public BetterJQueryDatepicker(Browser driver) {
        this.browser = driver;
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

    private void pickYear(int yearInt) {
        datepicker = browser.findElement(By.id("ui-datepicker-div"));
        String year = datepicker.findElement(By.className("ui-datepicker-year")).getText();

        if (Integer.parseInt(year) < yearInt) {
            while (Integer.parseInt(year) !=  yearInt) {
                datepicker.findElement(By.className("ui-datepicker-next")).click();
                datepicker = browser.findElement(By.id("datepicker"));
                year = datepicker.findElement(By.className("ui-datepicker-year")).getText();
            }
        } else if (Integer.parseInt(year) > yearInt) {
            while (Integer.parseInt(year) != yearInt) {
                datepicker.findElement(By.className("ui-datepicker-prev")).click();
                datepicker = browser.findElement(By.id("ui-datepicker-div"));
                year = datepicker.findElement(By.className("ui-datepicker-year")).getText();
            }
        }
    }

    private void pickMonth(int month1) {
        String month = datepicker.findElement(By.className("ui-datepicker-month")).getText();
        if (Month.valueOf(month.toUpperCase()).ordinal() < month1) {
            while (Month.valueOf(month.toUpperCase()).ordinal() != month1) {
                datepicker.findElement(By.className("ui-datepicker-next")).click();
                datepicker = browser.findElement(By.id("ui-datepicker-div"));
                month = datepicker.findElement(By.className("ui-datepicker-month")).getText();
            }
        } else if (Month.valueOf(month.toUpperCase()).ordinal() > month1) {
            while (Month.valueOf(month.toUpperCase()).ordinal() != month1) {
                datepicker.findElement(By.className("ui-datepicker-prev")).click();
                datepicker = browser.findElement(By.id("ui-datepicker-div"));
                month = datepicker.findElement(By.className("ui-datepicker-month")).getText();
            }
        }
    }

    private void pickDay(int day) {
        List<WebElement> tds = datepicker.findElements(By.tagName("td"));
        for (WebElement td : tds) {
            if (td.getText().equals(String.valueOf(day))) {
                td.click();
            }
        }
    }
}
