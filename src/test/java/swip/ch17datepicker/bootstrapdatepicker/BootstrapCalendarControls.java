package swip.ch17datepicker.bootstrapdatepicker;


import swip.ch15pageflow.framework.Browser;

import java.util.function.Function;

import static swip.ch17datepicker.bootstrapdatepicker.BootstrapByClassName.*;

public enum BootstrapCalendarControls implements Function<Browser, Void> {
    TRIGGER {
        public Void apply(Browser browser) {
            browser.untilFound(DATE_FIELD).click();
            return null;
        }
    },

    NEXT_MONTH {
        public Void apply(Browser browser) {
            browser.untilFound(CALENDAR)
                .untilFound(NEXT_MONTH_BUTTON).click();
            return null;
        }
    },

    PREVIOUS_MONTH {
        public Void apply(Browser browser) {
            browser.untilFound(CALENDAR)
                .untilFound(PREV_MONTH_BUTTON).click();
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
    }
}
