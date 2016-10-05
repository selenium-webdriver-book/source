package swb.ch18datepicker.material_ui.v1;

import swb.framework.Browser;

import java.util.function.Consumer;

import static swb.locators.material_ui.MaterialByXpath.*;


public enum MaterialCalendarControls implements Consumer<Browser> {    //<6>
    TRIGGER {
        @Override
        public void accept(Browser browser) {
             browser.click(TRIGGER_BY);
        }
    },
    NEXT_MONTH {
        @Override
        public void accept(Browser browser) {
            browser.click(NEXT_MONTH_BUTTON);          // <2>
        }

    },
    PREVIOUS_MONTH {
        @Override
        public void accept(Browser browser) {
            browser.click(PREV_MONTH_BUTTON);           //<3>
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