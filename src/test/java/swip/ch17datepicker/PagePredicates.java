package swip.ch17datepicker;


import swip.ch15pageflow.framework.Element;
import swip.ch15pageflow.framework.ExplicitWait;
import swip.ch17datepicker.jquerydatepicker.Id;

import java.util.Optional;
import java.util.function.Predicate;

public enum PagePredicates implements Predicate<ExplicitWait> {

    JQUERY_CALENDAR_NOT_DISPLAYED {
        @Override
        public boolean test(ExplicitWait explicitWait) {
            Optional<Element> element = explicitWait.optionalElement(Id.UI_DATEPICKER_DIV);
            return !element.isPresent() || !element.get().isDisplayed();
        }
    }

}
