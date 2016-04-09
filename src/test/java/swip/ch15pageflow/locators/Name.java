package swip.ch15pageflow.locators;

import org.openqa.selenium.By;

import java.util.function.Supplier;

/**
 * This enum is a Supplier of ByName from Selenium By API.
 */
public enum Name implements Supplier<By> {

    FILTER_EVENT("filter-events"),
    FILE("file"),
    Q("q"),
    MAILING_OPTION("customFieldDS.customfield_ROW0_value"),
    QUANTITY("cartDS.shoppingcart_ROW0_m_orderItemVector_ROW0_m_quantity");

    private final By by;

    Name(String id) {
        this.by = By.name(id);
    }

    /**
     * @return the by instance variable which is a ByName.
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