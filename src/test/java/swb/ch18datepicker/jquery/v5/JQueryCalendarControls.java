package swb.ch18datepicker.jquery.v5;

import swb.framework.Browser;

import java.util.function.Consumer;

import static swb.locators.jquery.JQueryByClassName.NEXT_MONTH_BUTTON;
import static swb.locators.jquery.JQueryByClassName.PREV_MONTH_BUTTON;
import static swb.locators.jquery.JQueryById.CALENDAR;
import static swb.locators.jquery.JQueryById.TRIGGER_BY;

public enum JQueryCalendarControls implements Consumer<Browser> {

    TRIGGER {
        @Override
        public void accept(Browser browser) {
            browser.click(TRIGGER_BY);
        }
    },
    NEXT_MONTH {
        @Override
        public void accept(Browser browser) {
            browser.await(CALENDAR).click(NEXT_MONTH_BUTTON);
        }
    },
    PREVIOUS_MONTH {
        @Override
        public void accept(Browser browser) {
            browser.await(CALENDAR).click(PREV_MONTH_BUTTON);
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
