package swip.ch17datepicker.jquerydatepicker.v3;


import swip.ch15pageflow.framework.v2.Element;
import swip.ch15pageflow.framework.v2.ExplicitWait;

import java.util.Optional;
import java.util.function.Predicate;

import static swip.ch17datepicker.jquerydatepicker.v3.JQueryById.UI_DATEPICKER_DIV;

public enum JQueryPredicates implements Predicate<ExplicitWait> {

    CALENDAR_CLOSED {
        @Override
        public boolean test(ExplicitWait explicitWait) {
            Optional<Element> element = explicitWait.optionalElement(UI_DATEPICKER_DIV);
            return !element.isPresent() || !element.get().isDisplayed();
        }
    }

}
