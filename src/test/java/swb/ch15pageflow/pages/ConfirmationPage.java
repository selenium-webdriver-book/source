package swb.ch15pageflow.pages;

import swb.framework.Browser;

import static swb.locators.ClassName.ORDER_NUMBER;
import static swb.locators.Id.ORDER_NUMBER_ID;

public class ConfirmationPage {

    private final Browser browser;

    public ConfirmationPage(Browser browser) {
        this.browser = browser;
    }

    public String getOrderNumber() {
        return browser.await(ORDER_NUMBER_ID).getText(ORDER_NUMBER);    //<2>
    }
}