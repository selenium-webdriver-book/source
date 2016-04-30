package swip.ch14elements.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch14elements.domain.OtherInformation;
import swip.ch14elements.pages.BookPage;
import swip.ch14elements.pages.ShoppingCartPage;
import swip.framework.Browser;
import swip.framework.BrowserRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class ShoppingCartPageIT {
    @Inject
    private Browser browser;

    private ShoppingCartPage page;

    private OtherInformation info = new OtherInformation(
        "no code",
        "joe@email.com",
        true,
        true,
        "Weekly newsletter--New books, updates, news, and special offers",
        "no comments"
    );

    @Before
    public void addBookToCart() throws InterruptedException {
        browser.get("/bookstore/books/selenium-webdriver-in-practice");
        BookPage bookPage = new BookPage(browser);
        bookPage.addToCart();
        bookPage.gotoCart();
        page = new ShoppingCartPage(browser);
    }

    @Test
    public void weShouldBeABleToCompleteOtherInformation() {
        page.setOtherInformation(info);
        assertEquals(this.info, page.getOtherInformation());
    }

}