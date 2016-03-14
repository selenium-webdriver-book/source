package swip.ch17datepicker.bootstrapdatepicker;


import swip.ch15pageflow.v2.framework.Browser;

import java.util.function.Consumer;

import static swip.ch17datepicker.bootstrapdatepicker.BootstrapByClassName.*;

public enum BootstrapCalendarControls implements Consumer<Browser> {
    TRIGGER {
        @Override
        public void accept(Browser browser) {
            browser.untilFound(DATE_FIELD).click();
        }
    },
    NEXT_MONTH {
        @Override
        public void accept(Browser browser) {
            browser.untilFound(CALENDAR)
                .click(NEXT_MONTH_BUTTON);
        }

    },
    PREVIOUS_MONTH {
        @Override
        public void accept(Browser browser) {
            browser.untilFound(CALENDAR)
                .click(PREV_MONTH_BUTTON);
        }
    },

    NEXT_YEAR {
        @Override
        public void accept(Browser browser) {
            for (int i = 0; i < 12; i++) {
                NEXT_MONTH.accept(browser);
            }
        }
    },
    PREVIOUS_YEAR {
        @Override
        public void accept(Browser browser) {
            for (int i = 0; i < 12; i++) {
                PREVIOUS_MONTH.accept(browser);
            }
        }
    }
}
