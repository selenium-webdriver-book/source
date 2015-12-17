package swip.ch15pageflow.pages;


import org.openqa.selenium.WebElement;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.locators.BookStoreId;
import swip.ch15pageflow.locators.TagName;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ErrorMessages {

    private final List<String> errorMessages;

    public ErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public ErrorMessages(Stream<String> errorMessages) {
        this(errorMessages.collect(toList()));
    }

    public ErrorMessages(Browser browser) {
        this(browser.findElement(BookStoreId.ERROR_MESSAGES).findElements(TagName.LI).map(WebElement::getText));
    }

    @Override
    public boolean equals(Object other) {
        return this == other ||
            (other instanceof ErrorMessages &&
                ((ErrorMessages) other).errorMessages.equals(this.errorMessages)
            );
    }

    @Override
    public String toString() {
        return errorMessages.toString();
    }
}
