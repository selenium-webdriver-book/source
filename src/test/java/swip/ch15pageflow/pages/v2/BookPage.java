package swip.ch15pageflow.pages.v2;

import swip.ch15pageflow.framework.v2.Browser;
import swip.ch15pageflow.framework.v2.Element;

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
        cartButton().click();
    }

    public Element cartButton() {
        return browser.untilFound(TOP_NAV)
            .untilFound(CART_BUTTON);
    }

}