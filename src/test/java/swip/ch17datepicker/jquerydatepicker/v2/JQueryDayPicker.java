package swip.ch17datepicker.jquerydatepicker.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import swip.ch15pageflow.framework.Browser;

import java.util.List;

public class JQueryDayPicker {

    private Browser browser;


    public JQueryDayPicker(Browser browser) {
        this.browser = browser;
    }

    public void pickDay(int day) {
        browser.findElement(By.id("ui-datepicker-div"))
            .findElement(By.linkText(String.valueOf(day))).click();

        new FluentWait<>(browser).until(
            (Browser b) -> b.findElements(By.id("ui-datepicker-div")).size() == 0 ||
                !b.findElements(By.id("ui-datepicker-div")).get(0).isDisplayed()
        );

    }

}
