package swip.ch17datepicker.datepicker;


import swip.ch15pageflow.framework.Browser;

import java.util.function.Function;

public interface DayLocatorFactory {

    Function<Browser, Void> forDay(int day);
}
