package swip.ch17jquerydatepicker;



import swip.ch17jquerydatepicker.framework.Element;
import swip.ch17jquerydatepicker.framework.ExplicitWait;

import java.util.Optional;
import java.util.function.Predicate;

import static swip.ch17jquerydatepicker.JQueryById.UI_DATEPICKER_DIV;

public enum JQueryPredicates implements Predicate<ExplicitWait> {

    CALENDAR_CLOSED {
        @Override
        public boolean test(ExplicitWait explicitWait) {
            Optional<Element> element = explicitWait.optionalElement(UI_DATEPICKER_DIV);
            return !element.isPresent() || !element.get().isDisplayed();
        }
    }

}
