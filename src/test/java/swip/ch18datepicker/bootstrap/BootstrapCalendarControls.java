package swip.ch18datepicker.bootstrap;


import swip.ch18datepicker.framework.Browser;

import java.util.function.Consumer;

import static swip.ch18datepicker.bootstrap.BootstrapByClassName.*;


public enum BootstrapCalendarControls implements Consumer<Browser> {    //<6>
    TRIGGER {
        @Override
        public void accept(Browser browser) {
            browser.click(DATE_FIELD);            // <1>
        }
    },
    NEXT_MONTH {
        @Override
        public void accept(Browser browser) {
            browser.untilFound(CALENDAR)
                .click(NEXT_MONTH_BUTTON);          // <2>
        }

    },
    PREVIOUS_MONTH {
        @Override
        public void accept(Browser browser) {
            browser.untilFound(CALENDAR)
                .click(PREV_MONTH_BUTTON);           //<3>
        }
    },

    NEXT_YEAR {
        @Override
        public void accept(Browser browser) {
            for (int i = 0; i < 12; i++) {       //<4>
                NEXT_MONTH.accept(browser);
            }
        }
    },
    PREVIOUS_YEAR {
        @Override
        public void accept(Browser browser) {
            for (int i = 0; i < 12; i++) {        // <5>
                PREVIOUS_MONTH.accept(browser);
            }
        }
    }
}