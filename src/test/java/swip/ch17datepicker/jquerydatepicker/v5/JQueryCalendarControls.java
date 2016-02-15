package swip.ch17datepicker.jquerydatepicker.v5;


import swip.ch15pageflow.framework.Browser;

import java.util.function.Function;

import static swip.ch17datepicker.jquerydatepicker.v3.JQueryByClassName.NEXT_MONTH_BUTTON;
import static swip.ch17datepicker.jquerydatepicker.v3.JQueryByClassName.PREV_MONTH_BUTTON;
import static swip.ch17datepicker.jquerydatepicker.v3.JQueryById.DATE_FIELD;
import static swip.ch17datepicker.jquerydatepicker.v3.JQueryById.UI_DATEPICKER_DIV;

public enum JQueryCalendarControls implements Function<Browser, Void> {
    TRIGGER {         //<1>
        public Void apply(Browser browser) {
            browser.untilFound(DATE_FIELD).click();
            return null;
        }
    },

    NEXT_MONTH {      //<2>
        public Void apply(Browser browser) {
            browser.untilFound(UI_DATEPICKER_DIV)
                .untilFound(NEXT_MONTH_BUTTON).click();
            return null;
        }
    },

    PREVIOUS_MONTH {     //<3>
        public Void apply(Browser browser) {
            browser.untilFound(UI_DATEPICKER_DIV)
                .untilFound(PREV_MONTH_BUTTON).click();
            return null;
        }
    },

    NEXT_YEAR {         //<4>
        public Void apply(Browser browser) {
            for (int i = 0; i < 12; i++) {
                NEXT_MONTH.apply(browser);
            }
            return null;
        }
    },
    PREVIOUS_YEAR {      //<5>
        public Void apply(Browser browser) {
            for (int i = 0; i < 12; i++) {
                PREVIOUS_MONTH.apply(browser);
            }
            return null;
        }
    }
}
