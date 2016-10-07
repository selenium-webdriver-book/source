package swb.ch14elements.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swb.ch14elements.pages.BookPage;
import swb.ch14elements.pages.NaiveShoppingCartPage;
import swb.framework.Browser;
import swb.framework.BrowserRunner;


import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(BrowserRunner.class)
public class NaiveShoppingCartPageIT {
    @Inject private Browser browser;

    private NaiveShoppingCartPage page;

    @Before
    public void addBookToCart() throws InterruptedException {
        browser.get("/bookstore/books/selenium-webdriver-book");
        BookPage bookPage = new BookPage(browser);
        bookPage.addToCart();
        bookPage.gotoCart();
        page = new NaiveShoppingCartPage(browser);
    }

    @Test
    public void weShouldBeABleToCompleteOtherInformationIndividually() {
        page.setComment("no comments");                                    //<1>
        page.setCoupon("no code");
        page.setEmail("joe@email.com");
        page.setRating(true);
        page.setSendOrderMessages(true);

        assertEquals("no comments", page.getComment());
        assertEquals("no code", page.getCoupon());
        assertEquals("joe@email.com", page.getEmail());
        assertTrue(page.isSendRatingEmail());
        assertTrue(page.isSendOrderMessages());
    }
}