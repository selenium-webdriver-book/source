package swip.ch14elements.pages;

import swip.framework.Browser;

import static swip.locators.ClassName.CART_BUTTON;
import static swip.locators.Id.TOP_NAV;
import static swip.locators.TagName.INPUT;

public class BookPage {

    private Browser browser;

    public BookPage(Browser browser) {
        this.browser = browser;
    }

    public void addToCart() throws InterruptedException {
        browser.findElements(INPUT)
            .filter(e -> e.getValue().equals("add to cart"))
            .findFirst()
            .get()
            .click();
    }

    public void gotoCart() {
        browser.await(TOP_NAV).click(CART_BUTTON);
    }
}