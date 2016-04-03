package swip.ch18datepicker.jquery.v3;

import swip.ch15pageflow.v2.framework.Browser;

import java.util.function.Consumer;

public class PreviousYear implements Consumer<Browser> {

    private final PreviousMonth previousMonth = new PreviousMonth();

    @Override
    public void accept(Browser browser) {
        for (int i = 0; i < 12; i++) {
            previousMonth.accept(browser);
        }
    }
}
