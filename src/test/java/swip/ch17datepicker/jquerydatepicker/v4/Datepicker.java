package swip.ch17datepicker.jquerydatepicker.v4;

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
        LocalDate.of(year, month.ordinal() + 1, day);     //<7>
        calendar.show();                               //<8>
        yearPicker.pickYear(year);                    //<9>
        monthPicker.pickMonth(month.ordinal());       //<10>
        dayPicker.pickDay(day);                        //<11>
    }

}
