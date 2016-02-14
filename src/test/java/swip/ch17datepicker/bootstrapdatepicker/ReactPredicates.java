package swip.ch17datepicker.bootstrapdatepicker;


import swip.ch15pageflow.framework.Element;
import swip.ch15pageflow.framework.ExplicitWait;

import java.util.Optional;
import java.util.function.Predicate;

import static swip.ch17datepicker.bootstrapdatepicker.BootstrapByClassName.CALENDAR;

public enum ReactPredicates implements Predicate<ExplicitWait> {

    REACT_CALENDAR_CLOSED {
        @Override
        public boolean test(ExplicitWait explicitWait) {
            Optional<Element> element = explicitWait.optionalElement(CALENDAR);
            return !element.isPresent() || !element.get().isDisplayed();
        }
    }

}