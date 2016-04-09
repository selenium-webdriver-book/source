package swip.ch18datepicker.jquery.v5;

import swip.ch15pageflow.framework.Browser;

import java.util.function.Consumer;

import static swip.ch17jquerydatepicker.locators.JQueryByClassName.NEXT_MONTH_BUTTON;
import static swip.ch17jquerydatepicker.locators.JQueryByClassName.PREV_MONTH_BUTTON;
import static swip.ch17jquerydatepicker.locators.JQueryById.DATE_FIELD;
import static swip.ch17jquerydatepicker.locators.JQueryById.CALENDAR;


public enum JQueryCalendarControls implements Consumer<Browser> {

    TRIGGER {      //<1>
        @Override public void accept(Browser browser) {
            browser.click(DATE_FIELD);
        }
    },
    NEXT_MONTH {    //<2>
        @Override public void accept(Browser browser) {
            browser.untilFound(CALENDAR)
                .click(NEXT_MONTH_BUTTON);
        }
    },
    PREVIOUS_MONTH {   //<3>
        @Override public void accept(Browser browser) {
            browser.untilFound(CALENDAR)
                .click(PREV_MONTH_BUTTON);
        }
    },
    NEXT_YEAR {         //<4>
        @Override public void accept(Browser browser) {
            for (int i = 0; i < 12; i++) {
                NEXT_MONTH.accept(browser);
            }
        }
    },
    PREVIOUS_YEAR {      //<5>
        @Override public void accept(Browser browser) {
            for (int i = 0; i < 12; i++) {
                PREVIOUS_MONTH.accept(browser);
            }
        }
    }
}
