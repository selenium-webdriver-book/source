package swip.ch17datepicker.reactdatepicker;


import swip.ch15pageflow.framework.Browser;

import java.util.function.Function;

import static swip.ch17datepicker.reactdatepicker.ReactClassName.*;

public enum ReactCalendarControls implements Function<Browser, Void> {
    TRIGGER {
        public Void apply(Browser browser) {
            browser.untilFound(ReactClassName.TRIGGER).click();
            return null;
        }
    },

    NEXT_MONTH {
        public Void apply(Browser browser) {
            browser.untilFound(CALENDAR)
                .untilFound(UI_DATEPICKER_NEXT).click();
            return null;
        }
    },

    PREVIOUS_MONTH {
        public Void apply(Browser browser) {
            browser.untilFound(CALENDAR)
                .untilFound(UI_DATEPICKER_PREV).click();
            return null;
        }
    },

    NEXT_YEAR {
        public Void apply(Browser browser) {
            for (int i = 0; i < 12; i++) {
                NEXT_MONTH.apply(browser);
            }
            return null;
        }
    },
    PREVIOUS_YEAR {
        public Void apply(Browser browser) {
            for (int i = 0; i < 12; i++) {
                PREVIOUS_MONTH.apply(browser);
            }
            return null;
        }
    },
    ;
}
