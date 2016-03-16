package swip.ch17datepicker.jquerydatepicker.v0;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import swip.ch15pageflow.v2.framework.Browser;
import swip.ch15pageflow.v2.framework.Element;

import java.time.Month;
import java.util.Calendar;


public class NaiveDatepicker {

    private final Browser browser;

    public NaiveDatepicker(Browser browser) {
        this.browser = browser;
    }

    public void pickDate(Calendar date) {
        //show - begin
        Element trigger = browser.untilFound(() -> By.id("datepicker"));
        trigger.click();
        //show – end

        int yearToPick = date.get(Calendar.YEAR);
        //pickYear - begin
        WebElement datepicker = browser.findElement(By.id("ui-datepicker-div"));

        String year = datepicker.findElement(
            By.className("ui-datepicker-year")).getText();

        if (Integer.parseInt(year) < yearToPick) {
            while (Integer.parseInt(year) != yearToPick) {
                datepicker.findElement(
                    By.className("ui-datepicker-next")).click();
                datepicker = browser.findElement(By.id("datepicker"));
                year = datepicker.findElement(
                    By.className("ui-datepicker-year")).getText();
            }
        } else if (Integer.parseInt(year) > yearToPick) {
            while (Integer.parseInt(year) != yearToPick) {
                datepicker.findElement(
                    By.className("ui-datepicker-prev")).click();
                datepicker = browser.findElement(
                    By.id("ui-datepicker-div"));
                year = datepicker.findElement(
                    By.className("ui-datepicker-year")).getText();
            }
        }
        //pickYear - end

        int monthToPick = date.get(Calendar.MONTH);

        //pickMonth - begin
        String month = datepicker.findElement(
            By.className("ui-datepicker-month")).getText();

        if (Month.valueOf(month.toUpperCase()).ordinal() < monthToPick) {
            while (Month.valueOf(month.toUpperCase()).ordinal() != monthToPick) {
                datepicker.findElement
                    (By.className("ui-datepicker-next")).click();
                datepicker = browser.findElement(By.id("ui-datepicker-div"));
                month = datepicker.findElement(
                    By.className("ui-datepicker-month")).getText();
            }
        } else if (Month.valueOf(month.toUpperCase()).ordinal() > monthToPick) {
            while (Month.valueOf(month.toUpperCase()).ordinal() != monthToPick) {
                datepicker.findElement(
                    By.className("ui-datepicker-prev")).click();
                datepicker = browser.findElement(By.id("ui-datepicker-div"));
                month = datepicker.findElement(
                    By.className("ui-datepicker-month")).getText();
            }
        }
        //pickMonth - end

        int dayToPick = date.get(Calendar.DAY_OF_MONTH);
        //pickDay - begin
        datepicker.findElement(By.linkText(String.valueOf(dayToPick))).click();

        new FluentWait<>(browser).until(
            (Browser b) -> b.findElements(By.id("ui-datepicker-div")).size() == 0 ||
                !b.findElements(By.id("ui-datepicker-div")).get(0).isDisplayed()
        );
        //pickDay - end
    }

    public String getDate() {
        return browser.findElement(By.id("datepicker")).getAttribute("value");
    }

}
