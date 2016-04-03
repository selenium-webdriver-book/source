package swip.ch17jquerydatepicker.v1;

import org.openqa.selenium.By;
import swip.ch15pageflow.framework.Browser;
import swip.ch17jquerydatepicker.JQueryById;
import swip.ch17jquerydatepicker.JQueryPredicates;


import java.time.LocalDate;
import java.time.Month;
import java.util.function.Supplier;

import static swip.ch17jquerydatepicker.JQueryByClassName.*;
import static swip.ch17jquerydatepicker.JQueryById.CALENDAR;


public class JQueryDatepicker {

    private final Browser browser;

    public JQueryDatepicker(Browser browser) {
        this.browser = browser;
    }

    public String getDate() {                                  //<7>
        return browser.getInputText(JQueryById.DATE_FIELD);
    }

    public void pick(Month month, int day, int year) {
        LocalDate.of(year, month, day);    //<1>
        show();                                          //<2>
        pickYear(year);                                  //<3>
        pickMonth(month.ordinal());                      //<4>
        pickDay(day);                                    //<5>
    }

    private void show() {
        browser.click(JQueryById.DATE_FIELD);
    }

    private void pickYear(int year) {
        if (displayedYear() < year) {        //<1>
            while (displayedYear() != year) {
                nextYear();
            }
        } else if (displayedYear() > year) {
            while (displayedYear() != year) {
                previousYear();
            }
        }
    }

    private void previousYear() {
        for (int i = 0; i < 12; i++) {
            previousMonth();
        }
    }

    private void nextYear() {
        for (int i = 0; i < 12; i++) {
            nextMonth();
        }
    }

    private int displayedYear() {
        String text = browser.untilFound(CALENDAR)
            .getText(YEAR);
        return Integer.parseInt(text);
    }

    private void pickMonth(int month) {
        if (displayedMonth() < month) {             //<2>
            while (displayedMonth() != month) {
                nextMonth();
            }
        } else if (displayedMonth() > month) {
            while (displayedMonth() != month) {
                previousMonth();
            }
        }
    }

    private void previousMonth() {
        browser.untilFound(CALENDAR)
            .click(PREV_MONTH_BUTTON);  //<3>
    }

    private void nextMonth() {
        browser.untilFound(CALENDAR)
            .click(NEXT_MONTH_BUTTON);  //<4>
    }

    private int displayedMonth() {
        String text = browser.untilFound(CALENDAR)
            .getText(MONTH).toUpperCase();
        return Month.valueOf(text).ordinal();   //<7>
    }

    private void pickDay(int day) {
        browser.untilFound(CALENDAR)
            .click(new Supplier<By>() {
                @Override
                public By get() {
                    return By.linkText(String.valueOf(day));
                }
            }); //<9>
        browser.until(JQueryPredicates.CALENDAR_CLOSED);  //<11>
    }
}
