package swip.ch17datepicker.jquerydatepicker.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import swip.ch15pageflow.framework.Browser;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


public class JQueryCalendar {

    private Browser browser;

    public JQueryCalendar(Browser browser) {
        this.browser = browser;
    }

    public void show() {
        WebElement element = browser.findElement(By.id("datepicker"));
        element.click();
    }
}
