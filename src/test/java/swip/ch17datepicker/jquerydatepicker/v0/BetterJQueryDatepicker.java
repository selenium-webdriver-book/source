package swip.ch17datepicker.jquerydatepicker.v0;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import swip.ch15pageflow.v2.framework.Browser;


import java.time.Month;
import java.util.Date;


public class BetterJQueryDatepicker {

    private final Browser browser;
    private WebElement datepicker;

    public BetterJQueryDatepicker(Browser driver) {
        this.browser = driver;
    }


    public void pickDate(Date date) {
        show();
        pickYear(date.getYear() + 1900);
        pickMonth(date.getMonth());
        pickDay(date.getDay() + 1);
    }

    public String getDate() {
        return browser.findElement(By.id("datepicker")).getAttribute("value");
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
        browser.findElement(By.id("ui-datepicker-div"))
            .findElement(By.linkText(String.valueOf(day))).click();

        new FluentWait<>(browser).until(
            (Browser b) -> b.findElements(By.id("ui-datepicker-div")).size() == 0 ||
                !b.findElements(By.id("ui-datepicker-div")).get(0).isDisplayed()
        );
    }
}
