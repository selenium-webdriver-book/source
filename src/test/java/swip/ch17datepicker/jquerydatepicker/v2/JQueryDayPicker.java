package swip.ch17datepicker.jquerydatepicker.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import swip.ch15pageflow.framework.Browser;

import java.util.List;

public class JQueryDayPicker {

    private Browser browser;


    public JQueryDayPicker(Browser browser) {
        this.browser = browser;
    }

    public void pickDay(int day) {
        List<WebElement> tds = calendar().findElements(By.tagName("td"));
        for (WebElement td : tds) {
            if (td.getText().equals(String.valueOf(day))) {
                td.click();
            }
        }
    }

    private WebElement calendar() {
        return browser.findElement(By.id("ui-datepicker-div"));   //<5>
    }

}
