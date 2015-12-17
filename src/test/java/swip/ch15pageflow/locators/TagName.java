package swip.ch15pageflow.locators;


import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.tagName;

/**
 * This enum is the Supplier of ByTagName from Selenium By API.
 */
public enum TagName implements Supplier<By> {
    A("a"),
    EM("em"),
    H1("h1"),
    H5("h5"),
    I("i"),
    P("p"),
    DIV("div"),
    IMG("img"),
    INPUT("input"),
    LI("li"),
    OPTION("option"),
    SPAN("span"),
    STRONG("strong"),
    TABLE("table"),
    TD("td"),
    TR("tr"),
    TH("th"),
    UL("ul");

    private final By by;

    TagName(String id) {
        this.by = tagName(id);
    }

    /**
     * @return the by instance variable which is a ByTagName.
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
