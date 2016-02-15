package swip.ch17datepicker.jquerydatepicker.v3;

import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.Element;
import swip.ch15pageflow.locators.TagName;

import java.util.stream.Stream;

import static swip.ch17datepicker.jquerydatepicker.v3.JQueryById.UI_DATEPICKER_DIV;

public class JQueryDayPicker {

    private Browser browser;


    public JQueryDayPicker(Browser browser) {
        this.browser = browser;
    }

    public void pickDay(int day) {
        Stream<Element> tds = calendar().findElements(TagName.TD);
        tds.filter((Element e) -> Integer.parseInt(e.getText()) == day)  //<7>
            .findFirst()                     //<8>
            .get()                           //<9>
            .click();
    }

    private Element calendar() {
        return browser.findElement(UI_DATEPICKER_DIV);   //<5>
    }

}
