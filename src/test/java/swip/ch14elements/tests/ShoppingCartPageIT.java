package swip.ch14elements.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch14elements.domain.MailingOption;
import swip.ch14elements.domain.OtherInformation;
import swip.ch14elements.v5.Browser;
import swip.ch14elements.v5.BrowserRunner;
import swip.ch14elements.pages.BookPage;
import swip.ch14elements.pages.NaiveShoppingCartPage;
import swip.ch14elements.pages.ShoppingCartPage;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        MailingOption.WEEKLY_NEWSLETTER,
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