package swip.ch17datepicker.jquerydatepicker.v5;


import swip.ch15pageflow.framework.Browser;

import java.util.function.Consumer;
import java.util.function.Function;

import static swip.ch17datepicker.jquerydatepicker.v3.JQueryByClassName.NEXT_MONTH_BUTTON;
import static swip.ch17datepicker.jquerydatepicker.v3.JQueryByClassName.PREV_MONTH_BUTTON;
import static swip.ch17datepicker.jquerydatepicker.v3.JQueryById.DATE_FIELD;
import static swip.ch17datepicker.jquerydatepicker.v3.JQueryById.UI_DATEPICKER_DIV;

public enum JQueryCalendarControls implements Consumer<Browser> {

    TRIGGER {       //<1>

        @Override
        public void accept(Browser browser) {
            browser.untilFound(DATE_FIELD).click();
        }
    },
    NEXT_MONTH {    //<2>

        @Override
        public void accept(Browser browser) {
            browser.untilFound(UI_DATEPICKER_DIV)
                .untilFound(NEXT_MONTH_BUTTON).click();
        }
    },
    PREVIOUS_MONTH {   //<3>

        @Override
        public void accept(Browser browser) {
            browser.untilFound(UI_DATEPICKER_DIV)
                .untilFound(PREV_MONTH_BUTTON).click();
        }
    },
    NEXT_YEAR {         //<4>

        @Override
        public void accept(Browser browser) {
            for (int i = 0; i < 12; i++) {
                NEXT_MONTH.accept(browser);
            }
        }
    },
    PREVIOUS_YEAR {      //<5>

        @Override
        public void accept(Browser browser) {
            for (int i = 0; i < 12; i++) {
                PREVIOUS_MONTH.accept(browser);
            }
        }
    }
}
