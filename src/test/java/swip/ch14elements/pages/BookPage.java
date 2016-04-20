package swip.ch14elements.pages;

import org.openqa.selenium.By;
import swip.ch14elements.framework.v5.Browser;

public class BookPage {

    private Browser browser;

    public BookPage(Browser browser) {
        this.browser = browser;
    }

    public void addToCart() throws InterruptedException {
        browser.findElements(By.tagName("input"))
            .stream()
            .filter(e -> e.getAttribute("value").equals("add to cart"))
            .findFirst()
            .get()
            .click();
    }

    public void gotoCart() {
        browser.untilFound(By.id("primary-navbar"))
            .untilFound(By.className("cart-button")).click();
    }
}