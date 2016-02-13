package swip.ch17datepicker.datepicker;


import swip.ch15pageflow.framework.Browser;

import java.time.LocalDate;
import java.time.Month;
import java.util.function.Function;

/**
 * A general purpose DatePicker can be used to pick a given date from
 * the calendar flyout provided by JavaScript framework.
 *
 * @author Yujun Liang
 * @since 0.1
 */
public class Datepicker {

    private final Browser browser;        //<1>
    private final Function<Browser, Void> trigger;            //<2>
    private final CalendarPicker yearPicker;                  //<3>
    private final CalendarPicker monthPicker;                 //<4>
    private final DayPicker dayPicker;        //<5>

    /**
     * Constructor of the DatePicker which taking a Calendar interface.
     *
     * @param browser
     * @param trigger
     * @param yearPicker
     * @param monthPicker
     * @param dayPicker
     */
    public Datepicker(Browser browser,
                      Function<Browser, Void> trigger,
                      CalendarPicker yearPicker,
                      CalendarPicker monthPicker,
                      DayPicker dayPicker) {     //<6>
        this.browser = browser;
        this.trigger = trigger;
        this.yearPicker = yearPicker;
        this.monthPicker = monthPicker;
        this.dayPicker = dayPicker;
    }

    /**
     * Pick a date by the given parameter.
     * for example,
     * datePicker.pick(Month.JULY, 31, 1999)
     *
     * @param month it need to be defined as an enum to make the code cleaner.
     * @param day   an integer representing the day appearing on the calendar
     * @param year  an ineger representing the year appearing on the calendar
     */
    public void pick(Month month, int day, int year) {  //<7>
        LocalDate.of(year, month.ordinal() + 1, day);   //<8>
        trigger.apply(browser);      //<9>
        yearPicker.pick(year);              //<10>
        monthPicker.pick(month.ordinal());  //<11>
        dayPicker.pick(day);                //<12>
    }
}