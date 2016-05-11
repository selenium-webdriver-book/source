package swip.ch17jquerydatepicker.jquery.v1;

import org.openqa.selenium.By;
import swip.framework.Browser;
import swip.framework.ElementVisible;
import swip.locators.jquery.JQueryById;

import java.time.LocalDate;
import java.time.Month;
import java.util.function.Supplier;

import static swip.locators.jquery.JQueryByClassName.*;
import static swip.locators.jquery.JQueryById.CALENDAR;

public class JQueryDatepicker {

    private final Browser browser;

    public JQueryDatepicker(Browser browser) {
        this.browser = browser;
    }

    public String getDate() {                                  //<7>
        return browser.getInputText(JQueryById.TRIGGER_BY);
    }

    public void pick(Month month, int day, int year) {
        LocalDate.of(year, month, day);    //<1>
        show();                                          //<2>
        pickYear(year);                                  //<3>
        pickMonth(month.ordinal());                      //<4>
        pickDay(day);                                    //<5>
    }

    private void show() {
        browser.click(JQueryById.TRIGGER_BY);
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

    private int displayYear() {
        String text = browser.await(CALENDAR).getText(YEAR);
        return Integer.parseInt(text);
    }

    private void pickYear(int year) {
        if (displayYear() < year) {        //<1>
            while (displayYear() != year) {
                nextYear();
            }
        } else if (displayYear() > year) {
            while (displayYear() != year) {
                previousYear();
            }
        }
    }

    private void previousMonth() {
        browser.await(CALENDAR).click(PREV_MONTH_BUTTON);  //<3>
    }

    private void nextMonth() {
        browser.await(CALENDAR).click(NEXT_MONTH_BUTTON);  //<4>
    }

    private int displayMonth() {
        String text = browser.await(CALENDAR).getUpperText(MONTH);
        return Month.valueOf(text).ordinal();   //<7>
    }

    private void pickMonth(int month) {
        if (displayMonth() < month) {             //<2>
            while (displayMonth() != month) {
                nextMonth();
            }
        } else if (displayMonth() > month) {
            while (displayMonth() != month) {
                previousMonth();
            }
        }
    }

    private void pickDay(int day) {
        browser.await(CALENDAR)
            .click(new Supplier<By>() {
                @Override
                public By get() {
                    return By.linkText(String.valueOf(day));
                }
            }); //<9>
        browser.await(new ElementVisible(CALENDAR).negate());  //<11>
    }
}
