package swip.ch17datepicker.bootstrapdatepicker;


import swip.ch15pageflow.framework.v2.Element;
import swip.ch15pageflow.framework.v2.ExplicitWait;

import java.util.Optional;
import java.util.function.Predicate;

import static swip.ch17datepicker.bootstrapdatepicker.BootstrapByClassName.CALENDAR;

public enum BootstrapPredicates implements Predicate<ExplicitWait> {

    CALENDAR_CLOSED {
        @Override
        public boolean test(ExplicitWait explicitWait) {
            Optional<Element> element = explicitWait.optionalElement(CALENDAR);
            return !element.isPresent() || !element.get().isDisplayed();
        }
    }

}
