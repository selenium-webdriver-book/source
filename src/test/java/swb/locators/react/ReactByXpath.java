package swb.locators.react;

import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.xpath;

/**
 * This enum is a Supplier of By.ByXpath from Selenium By API.
 */
public enum ReactByXpath implements Supplier<By> {

    TRIGGER_BY("//*[@id=\"app\"]/descendant::input");

    private final By by;

    ReactByXpath(String id) {
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