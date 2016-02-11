package swip.ch17datepicker;


import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.Element;
import swip.ch15pageflow.locators.TagName;
import swip.ch17datepicker.datepicker.DayLocatorFactory;
import swip.ch17datepicker.jquerydatepicker.ClassName;
import swip.ch17datepicker.jquerydatepicker.Id;

import java.util.function.Function;

public enum JQueryDayLocatorFactory implements DayLocatorFactory {

    JQUERY_DAY {
        public Function<Browser, Void> forDay(int day) {
            return (Browser browser) -> {
                browser.untilFound(Id.UI_DATEPICKER_DIV)
                    .untilFound(ClassName.UI_DATEPICKER_CALENDAR)
                    .findElements(TagName.TD).filter((Element e) -> Integer.parseInt(e.getText()) == day)
                    .findFirst()
                    .get()
                    .click();
                return null;
            };
        }
    }
}
