package swip.ch17datepicker.jquerydatepicker.v4;

import java.time.LocalDate;
import java.time.Month;


public class Datepicker {

    private Calendar calendar;
    private YearPicker yearControl;
    private MonthPicker monthControl;
    private DayPicker dayPicker;

    public Datepicker(Calendar calendar,
                      YearPicker yearControl,
                      MonthPicker monthControl,
                      DayPicker dayPicker) {
        this.calendar = calendar;
        this.yearControl = yearControl;
        this.monthControl = monthControl;
        this.dayPicker = dayPicker;
    }

    public void pick(Month month, int day, int year) {
        LocalDate.of(year, month.ordinal() + 1, day);
        calendar.show();
        yearControl.pickYear(year);
        monthControl.pickMonth(month.ordinal());
        dayPicker.pickDay(day);
    }

}
