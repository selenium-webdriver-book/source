package swip.ch17datepicker.v2;

import java.time.LocalDate;
import java.time.Month;


public class JQueryDatepicker {

    private final JQueryCalendar calendar;
    private final JQueryYearPicker yearPicker;
    private final JQueryMonthPicker monthPicker;
    private final JQueryDayPicker dayPicker;

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
        LocalDate.of(year, month, day);
        calendar.show();
        yearPicker.pickYear(year);
        monthPicker.pickMonth(month.ordinal());
        dayPicker.pickDay(day);
    }

}
