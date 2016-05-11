package swip.ch15pageflow.pages;

import swip.framework.Browser;

import static swip.locators.ClassName.ORDER_NUMBER;
import static swip.locators.Id.ORDER_NUMBER_ID;

public class ConfirmationPage {

    private final Browser browser;

    public ConfirmationPage(Browser browser) {
        this.browser = browser;
    }

    public String getOrderNumber() {
        return browser.await(ORDER_NUMBER_ID).getText(ORDER_NUMBER);    //<2>
    }
}