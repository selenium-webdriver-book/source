package swb.framework.datepicker;

import org.junit.Test;

import java.time.DateTimeException;
import java.time.Month;


public class DatepickerTest {

    Datepicker datepicker = new Datepicker(null, null, null, null);

    @Test(expected = DateTimeException.class)
    public void pick() throws Exception {
        datepicker.pick(Month.APRIL, 31, 2012);
    }

}