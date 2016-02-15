package swip.ch17datepicker.jquerydatepicker.v2;

import java.time.LocalDate;
import java.time.Month;


public class JQueryDatepicker {

    private JQueryCalendar calendar;
    private JQueryYearControl yearControl;
    private JQueryMonthControl monthControl;
    private JQueryDayPicker dayPicker;

    public JQueryDatepicker(JQueryCalendar calendar,
                            JQueryYearControl yearControl,
                            JQueryMonthControl monthControl,
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
