package swb.ch18datepicker.material_ui.v1;

import swb.ch18datepicker.datepicker.v1.DayPicker;
import swb.framework.Browser;
import swb.framework.ElementVisible;

import static swb.locators.TagName.BUTTON;
import static swb.locators.material_ui.MaterialByXpath.CALENDAR;
import static swb.locators.material_ui.MaterialByXpath.OK_BUTTON;

public class MaterialDayPicker implements DayPicker {

    private Browser browser;

    public MaterialDayPicker(Browser browser) {
        this.browser = browser;
    }

    @Override
    public void pick(int day) {
        browser.await(CALENDAR).findElements(BUTTON)
            .filter(e -> e.getText().equals(String.valueOf(day)))
            .findFirst()
            .get()
            .click();
        browser.click(OK_BUTTON);
        browser.await(new ElementVisible(CALENDAR).negate());
    }
}
