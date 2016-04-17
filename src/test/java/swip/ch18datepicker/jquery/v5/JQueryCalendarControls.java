package swip.ch18datepicker.jquery.v5;

import swip.framework.Browser;

import java.util.function.Consumer;

import static swip.locators.jquery.JQueryByClassName.NEXT_MONTH_BUTTON;
import static swip.locators.jquery.JQueryByClassName.PREV_MONTH_BUTTON;
import static swip.locators.jquery.JQueryById.CALENDAR;
import static swip.locators.jquery.JQueryById.TRIGGER_BY;

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
            browser.untilFound(CALENDAR).click(NEXT_MONTH_BUTTON);
        }
    },
    PREVIOUS_MONTH {
        @Override
        public void accept(Browser browser) {
            browser.untilFound(CALENDAR).click(PREV_MONTH_BUTTON);
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
