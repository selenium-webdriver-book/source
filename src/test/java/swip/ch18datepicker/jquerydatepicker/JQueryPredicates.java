package swip.ch18datepicker.jquerydatepicker;


import swip.ch15pageflow.v2.framework.Element;
import swip.ch15pageflow.v2.framework.ExplicitWait;

import java.util.Optional;
import java.util.function.Predicate;

import static swip.ch17datepicker.jquerydatepicker.JQueryById.UI_DATEPICKER_DIV;

public enum JQueryPredicates implements Predicate<ExplicitWait> {

    CALENDAR_CLOSED {
        @Override
        public boolean test(ExplicitWait explicitWait) {
            Optional<Element> element = explicitWait.optionalElement(UI_DATEPICKER_DIV);
            return !element.isPresent() || !element.get().isDisplayed();
        }
    }

}
