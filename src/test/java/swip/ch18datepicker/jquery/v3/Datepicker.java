package swip.ch18datepicker.jquery.v3;

import swip.framework.datepicker.Calendar;
import swip.framework.datepicker.DayPicker;

import java.time.LocalDate;
import java.time.Month;

public class Datepicker {

    private final Calendar calendar;         //<1>
    private final YearPicker yearPicker;      //<2>
    private final MonthPicker monthPicker;     //<3>
    private final DayPicker dayPicker;        //<4>

    public Datepicker(Calendar calendar,
                      YearPicker yearPicker,
                      MonthPicker monthPicker,
                      DayPicker dayPicker) {
        this.calendar = calendar;
        this.yearPicker = yearPicker;
        this.monthPicker = monthPicker;
        this.dayPicker = dayPicker;
    }                                        //<5>

    public void pick(Month month, int day, int year) {   //<6>
        LocalDate.of(year, month, day);     //<7>
        calendar.show();                               //<8>
        yearPicker.pick(year);                    //<9>
        monthPicker.pick(month.ordinal());       //<10>
        dayPicker.pick(day);                        //<11>
    }

}
