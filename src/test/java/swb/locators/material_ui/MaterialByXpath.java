package swb.locators.material_ui;

import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.xpath;

/**
 * This enum is a Supplier of By.ByXpath from Selenium By API.
 */
public enum MaterialByXpath implements Supplier<By> {

    TRIGGER_BY("//*[@id=\"mui-id-2\"]"),
    CALENDAR("/html/body/div[2]/div/div[1]/div/div/div[1]"),
    OK_BUTTON("/html/body/div[2]/div/div[1]/div/div/div[2]/button[2]/div/span"),
    NEXT_MONTH_BUTTON("/html/body/div[2]/div/div[1]/div/div/div[1]/div/div[3]/div[1]/div[3]/button"),
    PREV_MONTH_BUTTON("/html/body/div[2]/div/div[1]/div/div/div[1]/div/div[3]/div[1]/div[2]/button"),
    DISPLAY_MONTH_YEAR("/html/body/div[2]/div/div[1]/div/div/div[1]/div/div[3]/div[1]/div[1]/div/div");

    private final By by;

    MaterialByXpath(String id) {
        this.by = xpath(id);
    }

    /**
     * @return the by instance variable which is a By.ByXpath.
     */
    @Override
    public By get() {
        return by;
    }

    @Override
    public String toString() {
        return by.toString();
    }
}