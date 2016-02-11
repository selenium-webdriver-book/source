package swip.ch17datepicker.datepicker;

import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.ExplicitWait;

import java.util.function.Function;
import java.util.function.Predicate;

public class CalendarBuilder {

    private Browser page;
    private Function<Browser, Void> trigger;
    private Function<Browser, Integer> displayedYear;
    private Function<Browser, Integer> displayedMonth;
    private Function<Browser, Void> previousMonth;
    private Function<Browser, Void> nextMonth;
    private DayLocatorFactory dayLocatorFactory;
    private Predicate<ExplicitWait> calendarClosed;


    public CalendarBuilder withPage(Browser page) {
        this.page = page;
        return this;
    }

    public CalendarBuilder withTrigger(Function<Browser, Void> trigger) {
        this.trigger = trigger;
        return this;
    }

    public CalendarBuilder withDisplayedYear(Function<Browser, Integer> displayedYear) {
        this.displayedYear = displayedYear;
        return this;
    }

    public CalendarBuilder withDisplayedMonth(Function<Browser, Integer> displayedMonth) {
        this.displayedMonth = displayedMonth;
        return this;
    }

    public CalendarBuilder withPreviousMonth(Function<Browser, Void> previousMonth) {
        this.previousMonth = previousMonth;
        return this;
    }

    public CalendarBuilder withNextMonth(Function<Browser, Void> nextMonth) {
        this.nextMonth = nextMonth;
        return this;
    }

    public CalendarBuilder withDayLocatorFactory(DayLocatorFactory dayLocatorFactory) {
        this.dayLocatorFactory = dayLocatorFactory;
        return this;
    }

    public CalendarBuilder withCalendarClosed(Predicate<ExplicitWait> calendarClosed) {
        this.calendarClosed = calendarClosed;
        return this;
    }

    public Calendar build() {
        return new Calendar(page, trigger, displayedYear, displayedMonth, previousMonth, nextMonth, dayLocatorFactory, calendarClosed);
    }


}
