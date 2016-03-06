package swip.ch14elements;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch14elements.domain.MailingOption;
import swip.ch14elements.domain.OtherInformation;
import swip.ch14elements.framework.Browser;
import swip.ch14elements.framework.BrowserRunner;
import swip.ch14elements.pages.BookPage;
import swip.ch14elements.pages.NaiveShoppingCartPage;
import swip.ch14elements.pages.ShoppingCartPage;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(BrowserRunner.class)
public class ShoppingCartPageIT {
    @Inject private Browser browser;

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
    }

    @Test
    public void weShouldBeABleToCompleteOtherInformationIndividually() {
        NaiveShoppingCartPage page = new NaiveShoppingCartPage(browser);
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

    @Test
    public void weShouldBeABleToCompleteOtherInformation() {
        ShoppingCartPage page = new ShoppingCartPage(browser);
        page.setOtherInformation(info);

        assertEquals(this.info, page.getOtherInformation());
    }

}