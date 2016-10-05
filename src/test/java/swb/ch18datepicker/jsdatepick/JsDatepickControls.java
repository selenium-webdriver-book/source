package swb.ch18datepicker.jsdatepick;

import swb.framework.Browser;

import java.util.function.Consumer;

import static swb.locators.jsdatepick.JsDatepickByClassName.*;
import static swb.locators.jsdatepick.JsDatepickById.TRIGGER_BY;

public enum JsDatepickControls implements Consumer<Browser> {    //<6>
    TRIGGER {
        @Override
        public void accept(Browser browser) {
            browser.click(TRIGGER_BY);            // <1>
        }
    },
    NEXT_MONTH {
        @Override
        public void accept(Browser browser) {
            browser.await(CALENDAR).click(NEXT_MONTH_BUTTON);          // <2>
        }

    },
    PREVIOUS_MONTH {
        @Override
        public void accept(Browser browser) {
            browser.await(CALENDAR).click(PREV_MONTH_BUTTON);           //<3>
        }
    },
    NEXT_YEAR {
        @Override
        public void accept(Browser browser) {
            browser.await(CALENDAR).click(NEXT_YEAR_BUTTON);           //<3>
        }
    },
    PREVIOUS_YEAR {
        @Override
        public void accept(Browser browser) {
            browser.await(CALENDAR).click(PREV_YEAR_BUTTON);           //<3>
        }
    }
}