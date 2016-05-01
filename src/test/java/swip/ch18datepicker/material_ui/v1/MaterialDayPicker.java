package swip.ch18datepicker.material_ui.v1;

import swip.ch18datepicker.datepicker.v1.DayPicker;
import swip.framework.Browser;
import swip.framework.ElementVisible;

import static swip.locators.TagName.BUTTON;
import static swip.locators.material_ui.MaterialByXpath.CALENDAR;
import static swip.locators.material_ui.MaterialByXpath.OK_BUTTON;

public class MaterialDayPicker implements DayPicker {

    private Browser browser;

    public MaterialDayPicker(Browser browser) {
        this.browser = browser;
    }

    @Override
    public void pick(int day) {
        browser.untilFound(CALENDAR).findElements(BUTTON)
            .filter(e -> e.getText().equals(String.valueOf(day)))
            .findFirst()
            .get()
            .click();
        browser.click(OK_BUTTON);
        browser.until(new ElementVisible(CALENDAR).negate());
    }
}
