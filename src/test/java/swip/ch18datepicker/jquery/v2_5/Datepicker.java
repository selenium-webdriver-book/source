package swip.ch18datepicker.jquery.v2_5;

import swip.framework.datepicker.Calendar;
import swip.framework.datepicker.DayPicker;

import java.time.LocalDate;
import java.time.Month;

/**
 * A general purpose DatePicker can be used to pick a given date from
 * the calendar flyout provided by JavaScript framework.
 *
 * @author Yujun Liang
 * @since 0.1
 */
public class Datepicker {

    private final Calendar calendar;           //<1>
    private final YearPicker yearPicker;                  //<2>
    private final MonthPicker monthPicker;                 //<3>
    private final DayPicker dayPicker;        //<4>

    /**
     * Constructor of the Datepicker
     *
     * @param calendar    calendar
     * @param yearPicker  yearPicker
     * @param monthPicker monthPicker
     * @param dayPicker   dayPicker
     */
    public Datepicker(Calendar calendar,
                      YearPicker yearPicker,
                      MonthPicker monthPicker,
                      DayPicker dayPicker) {
        this.calendar = calendar;
        this.yearPicker = yearPicker;
        this.monthPicker = monthPicker;
        this.dayPicker = dayPicker;
    }   //<5>

    /**
     * Pick a date by the given parameter.
     * for example,
     * datePicker.pick(Month.JULY, 31, 1999)
     *
     * @param month it need to be defined as an enum to make the code cleaner.
     * @param day   an integer representing the day appearing on the calendar
     * @param year  an ineger representing the year appearing on the calendar
     */
    public void pick(Month month, int day, int year) {  //<6>
        LocalDate.of(year, month, day);
        calendar.show();      //<7>
        yearPicker.pick(year);              //<8>
        monthPicker.pick(month.ordinal());  //<9>
        dayPicker.pick(day);                //<10>
    }
}