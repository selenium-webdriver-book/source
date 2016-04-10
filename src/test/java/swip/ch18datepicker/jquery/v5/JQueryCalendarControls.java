package swip.ch18datepicker.jquery.v5;

import swip.ch15pageflow.framework.Browser;
import swip.ch17jquerydatepicker.locators.JQueryById;

import java.util.function.Consumer;

import static swip.ch17jquerydatepicker.locators.JQueryByClassName.NEXT_MONTH_BUTTON;
import static swip.ch17jquerydatepicker.locators.JQueryByClassName.PREV_MONTH_BUTTON;
import static swip.ch17jquerydatepicker.locators.JQueryById.CALENDAR;


public enum JQueryCalendarControls implements Consumer<Browser> {

    TRIGGER {
        @Override
        public void accept(Browser browser) {
            browser.click(JQueryById.TRIGGER_BY);
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
