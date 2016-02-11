package swip.ch17datepicker.datepicker;


import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.ExplicitWait;

import java.util.function.Function;
import java.util.function.Predicate;


public class Calendar {

    private final Browser browser;                            //<1>
    private final Function<Browser, Void> trigger;            //<2>
    private final Function<Browser, Integer> displayedYear;   //<3>
    private final Function<Browser, Integer> displayedMonth;  //<4>
    private final Function<Browser, Void> previousMonth;      //<5>
    private final Function<Browser, Void> nextMonth;          //<6>
    private final DayLocatorFactory dayLocatorFactory;        //<7>
    private final Predicate<ExplicitWait> calendarClosed;     //<8>

    /**
     * Constructor of the Calendar, an active browser and locators
     * of the trigger element and other calendar control buttons.
     *
     * @param page              browser
     * @param trigger           locator to trigger the display of the calendar
     * @param displayedYear     displayedValue year
     * @param displayedMonth    displayedValue month
     * @param previousMonth     previous month
     * @param nextMonth         next month
     * @param dayLocatorFactory day
     * @param calendarClosed    whether calendar is closed
     */
    Calendar(Browser page,
             Function<Browser, Void> trigger,
             Function<Browser, Integer> displayedYear,
             Function<Browser, Integer> displayedMonth,
             Function<Browser, Void> previousMonth,
             Function<Browser, Void> nextMonth,
             DayLocatorFactory dayLocatorFactory,
             Predicate<ExplicitWait> calendarClosed) {         //<9>
        this.browser = page;
        this.trigger = trigger;
        this.displayedYear = displayedYear;
        this.displayedMonth = displayedMonth;
        this.previousMonth = previousMonth;
        this.nextMonth = nextMonth;
        this.dayLocatorFactory = dayLocatorFactory;
        this.calendarClosed = calendarClosed;
    }

    /**
     * Pop up the date picker calendar.
     */
    public void show() {
        trigger.apply(browser);     //<10>
    }

    /**
     * Read the displayedValue year from the calendar.
     *
     * @return displayedValue year
     */
    public int displayedYear() {
        return displayedYear.apply(browser);
    }

    /**
     * Read the displayedValue month from the calendar.
     *
     * @return displayedValue month
     */
    public int displayedMonth() {
        return displayedMonth.apply(browser);
    }

    /**
     * Pick the day from the calendar.
     *
     * @param day day
     */
    public void pickDay(int day) {
        dayLocatorFactory.forDay(day).apply(browser);
        browser.until(calendarClosed);                 //<11>
    }

    /**
     * Click the previous month button.
     */
    void previousMonth() {
        previousMonth.apply(browser);
    }

    /**
     * Click the next month button.
     */
    void nextMonth() {
        nextMonth.apply(browser);
    }

    /**
     * Clicking previous year button once, or clicking the previous month
     * button 12 times if the next year button is not present on the calendar.
     */
    void previousYear() {
        for (int i = 0; i < 12; i++) {
            previousMonth();
        }
    }

    /**
     * Clicking next year button once, or clicking the next month button
     * 12 times if the next year button is not present on the calendar.
     */
    void nextYear() {
        for (int i = 0; i < 12; i++) {
            nextMonth();
        }
    }
}
