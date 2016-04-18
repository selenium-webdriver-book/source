package swip.ch17reactdatepicker.react.v2;

import java.time.LocalDate;
import java.time.Month;

public class ReactDatepicker {

    private final ReactCalendar calendar;
    private final ReactYearPicker yearPicker;
    private final ReactMonthPicker monthPicker;
    private final ReactDayPicker dayPicker;

    public ReactDatepicker(ReactCalendar calendar,
                           ReactYearPicker yearPicker,
                           ReactMonthPicker monthPicker,
                           ReactDayPicker dayPicker) {
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
