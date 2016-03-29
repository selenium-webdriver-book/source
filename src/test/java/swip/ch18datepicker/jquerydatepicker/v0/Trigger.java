package swip.ch18datepicker.jquerydatepicker.v0;

import swip.ch15pageflow.v2.framework.Browser;
import swip.ch17datepicker.JQueryById;

public class Trigger implements Consumer<Browser>{

    @Override
    public void accept(Browser browser) {
        browser.click(JQueryById.DATE_FIELD);
    }
}
