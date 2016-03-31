package swip.ch18datepicker.jquery.v3;

import swip.ch18datepicker.framework.Browser;

import java.util.function.Consumer;


public class NextYear implements Consumer<Browser> {

    private final NextMonth nextMonth = new NextMonth();

    @Override
    public void accept(Browser browser) {
        for (int i = 0; i < 12; i++) {
            nextMonth.accept(browser);
        }
    }
}
