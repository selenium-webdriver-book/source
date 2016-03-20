package swip.ch17datepicker.jquerydatepicker.v1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import swip.ch15pageflow.v2.framework.Browser;

import java.time.LocalDate;
import java.time.Month;


public class JQueryDatepicker {

    private final Browser browser;

    public JQueryDatepicker(Browser browser) {
        this.browser = browser;
    }

    public void pick(Month month, int day, int year) {
        LocalDate.of(year, month.ordinal() + 1, day);    //<1>
        show();                                          //<2>
        pickYear(year);                                  //<3>
        pickMonth(month.ordinal());                      //<4>
        pickDay(day);                                    //<5>
    }

    public String getDate() {
        return browser.findElement(By.id("datepicker")).getAttribute("value");
    }

    private void show() {
        WebElement element = browser.findElement(By.id("datepicker"));
        element.click();
    }

    private void pickYear(int year) {
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

    private int displayedYear() {
        return Integer.parseInt(
            browser.findElement(By.id("ui-datepicker-div"))
                .findElement(By.className("ui-datepicker-year")).getText()
        );
    }

    private void pickMonth(int month) {
        int difference = displayedMonth() - month;
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
        browser.findElement(By.id("ui-datepicker-div"))
            .findElement(By.className("ui-datepicker-prev"))
            .click();  //<3>
    }

    private void nextMonth() {
        browser.findElement(By.id("ui-datepicker-div"))
            .findElement(By.className("ui-datepicker-next"))
            .click();  //<4>
    }


    private int displayedMonth() {
        return Month.valueOf(
            browser.findElement(By.id("ui-datepicker-div"))
                .findElement(By.className("ui-datepicker-month"))
                .getText()
                .toUpperCase()
        ).ordinal();   //<7>
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
