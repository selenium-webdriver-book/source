package swip.ch17datepicker.jquerydatepicker.v3;

import java.time.LocalDate;
import java.time.Month;


public class JQueryDatepicker {

    private JQueryCalendar calendar;
    private JQueryYearPicker yearPicker;
    private JQueryMonthPicker monthPicker;
    private JQueryDayPicker dayPicker;

    public JQueryDatepicker(JQueryCalendar calendar,
                            JQueryYearPicker yearPicker,
                            JQueryMonthPicker monthPicker,
                            JQueryDayPicker dayPicker) {
        this.calendar = calendar;
        this.yearPicker = yearPicker;
        this.monthPicker = monthPicker;
        this.dayPicker = dayPicker;
    }

    public void pick(Month month, int day, int year) {
        LocalDate.of(year, month.ordinal() + 1, day);
        calendar.show();
        yearPicker.pickYear(year);
        monthPicker.pickMonth(month.ordinal());
        dayPicker.pickDay(day);
    }

}
