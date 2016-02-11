package swip.ch17datepicker.datepicker;


import java.time.LocalDate;
import java.time.Month;

import static swip.ch17datepicker.datepicker.CalendarFlipper.MONTH_FLIPPER;
import static swip.ch17datepicker.datepicker.CalendarFlipper.YEAR_FLIPPER;

/**
 * A general purpose DatePicker can be used to pick a given date from
 * the calendar flyout provided by JavaScript framework.
 *
 * @author Yujun Liang
 * @since 0.1
 */
public class Datepicker {

    private final Calendar calendar;

    /**
     * Constructor of the DatePicker which taking a Calendar interface.
     *
     * @param calendar calendar
     */
    public Datepicker(Calendar calendar) {
        this.calendar = calendar;
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
    public void pick(Month month, int day, int year) {
        LocalDate.of(year, month.ordinal() + 1, day);
        calendar.show();
        YEAR_FLIPPER.flip(calendar, year);
        MONTH_FLIPPER.flip(calendar, month.ordinal());
        calendar.pickDay(day);
    }
}