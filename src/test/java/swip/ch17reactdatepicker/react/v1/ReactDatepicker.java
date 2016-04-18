package swip.ch17reactdatepicker.react.v1;

import swip.framework.Browser;
import swip.framework.ElementVisible;

import java.time.LocalDate;
import java.time.Month;

import static java.lang.Integer.parseInt;
import static swip.locators.StringToMonth.TO_MONTH;
import static swip.locators.TagName.DIV;
import static swip.locators.react.ReactByClassName.*;
import static swip.locators.react.ReactByXpath.TRIGGER_BY;

public class ReactDatepicker {

    private final Browser browser;

    public ReactDatepicker(Browser browser) {
        this.browser = browser;
    }

    public String getDate() {                                  //<7>
        return browser.getInputText(TRIGGER_BY);
    }

    public void pick(Month month, int day, int year) {
        LocalDate.of(year, month, day);    //<1>
        show();                                          //<2>
        pickYear(year);                                  //<3>
        pickMonth(month.ordinal());                      //<4>
        pickDay(day);                                    //<5>
    }

    private void show() {
        browser.click(TRIGGER_BY);
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
        return parseInt(extract(browser, 1));       //<1>
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
        browser.untilFound(CALENDAR).click(PREV_MONTH_BUTTON);  //<3>
    }

    private void nextMonth() {
        browser.untilFound(CALENDAR).click(NEXT_MONTH_BUTTON);  //<4>
    }

    private int displayMonth() {
        return TO_MONTH.apply(extract(browser, 0)).ordinal();       //<1>
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
        browser.untilFound(CALENDAR).findElements(DIV)
            .filter(e -> e.getText().equals(String.valueOf(day)))
            .findFirst()
            .get()
            .click();
        browser.until(new ElementVisible(CALENDAR).negate());
    }

    private static String extract(Browser browser, int i) {  //<1>
        return browser.untilFound(CALENDAR).getText(DISPLAY_MONTH_YEAR).split(" ")[i];
    }

}
