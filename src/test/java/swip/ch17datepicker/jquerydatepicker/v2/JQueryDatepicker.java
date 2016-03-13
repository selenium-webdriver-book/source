package swip.ch17datepicker.jquerydatepicker.v2;

import java.time.LocalDate;
import java.time.Month;


public class JQueryDatepicker {

    private final JQueryCalendar calendar;
    private final JQueryYearPicker yearControl;
    private final JQueryMonthPicker monthControl;
    private final JQueryDayPicker dayPicker;

    public JQueryDatepicker(JQueryCalendar calendar,
                            JQueryYearPicker yearControl,
                            JQueryMonthPicker monthControl,
                            JQueryDayPicker dayPicker) {
        this.calendar = calendar;
        this.yearControl = yearControl;
        this.monthControl = monthControl;
        this.dayPicker = dayPicker;
    }

    public void pick(Month month, int day, int year) {
        LocalDate.of(year, month.ordinal() + 1, day);   //<1>
        calendar.show();                       //<2>
        yearControl.pickYear(year);                 //<3>
        monthControl.pickMonth(month.ordinal());      //<4>
        dayPicker.pickDay(day);                       //<5>
    }

}
