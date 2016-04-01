package swip.ch18datepicker.jquery.v3;


import swip.ch18datepicker.framework.Browser;

import java.time.Month;

import static swip.ch17jquerydatepicker.JQueryById.DATE_FIELD;


public class JQueryDatepickerPage {

    private final Browser browser;    //<1>

    private final Datepicker datepicker;   //<2>

    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }  //<9>

    public String getDate() {
        return browser.getInputText(DATE_FIELD);
    }       //<10>

    public JQueryDatepickerPage(Browser b) {   //<3>
        this.browser = b;
        this.datepicker = new Datepicker(  //<4>
            new Calendar(browser, new Trigger()),
            new YearPicker(browser, new PreviousYear(), new NextYear(), new DisplayYear()),
            new MonthPicker(browser, new PreviousMonth(), new NextMonth(), new DisplayMonth()),
            new JQueryDayPicker(browser)
        );
    }
}
