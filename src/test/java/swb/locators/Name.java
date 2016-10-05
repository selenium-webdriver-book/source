package swb.locators;

import org.openqa.selenium.By;

import java.util.function.Supplier;

/**
 * This enum is a Supplier of ByName from Selenium By API.
 */
public enum Name implements Supplier<By> {

    MAILING_OPTION("customFieldDS.customfield_ROW0_value"),
    EMAIL("email"),
    PASSWORD("password"),
    TERMS("terms"),
    CONTACT("contact"),
    FREQUENCY("frequency"),
    INTEREST("interest"),
    TELLUS("tellus"),
    HEAR_ABOUT("hearAbout"),

    ;

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