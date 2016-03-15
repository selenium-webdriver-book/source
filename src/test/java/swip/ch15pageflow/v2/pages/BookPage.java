package swip.ch15pageflow.v2.pages;

import swip.ch15pageflow.v2.framework.Browser;

import static swip.ch15pageflow.locators.ClassName.CART_BUTTON;
import static swip.ch15pageflow.locators.Id.TOP_NAV;
import static swip.ch15pageflow.locators.TagName.INPUT;

public class BookPage {

    private Browser browser;

    public BookPage(Browser browser) {
        this.browser = browser;
    }

    public void addToCart() {
        browser.findElements(INPUT)
            .filter((e) -> e.getAttribute("value").equals("add to cart"))
            .findFirst()
            .get()
            .click();
    }

    public void gotoCart() {
        browser.untilFound(TOP_NAV).click(CART_BUTTON);
    }

}