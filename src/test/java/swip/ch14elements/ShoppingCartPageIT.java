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
        page.setComment(info.comments);                                    //<1>
        page.setCoupon(info.couponCode);
        page.setEmail(info.email);
        page.setRating(info.sendRatingEmail);
        page.setSendOrderMessages(info.sendOrdersToEmail);

        assertEquals(info.comments, page.getComment());
        assertEquals(info.couponCode, page.getCoupon());
        assertEquals(info.email, page.getEmail());
        assertEquals(info.sendRatingEmail, page.isSendRatingEmail());
        assertEquals(info.sendOrdersToEmail, page.isSendOrderMessages());
    }

    @Test
    public void weShouldBeABleToCompleteOtherInformation() {
        ShoppingCartPage page = new ShoppingCartPage(browser);
        page.setOtherInformation(info);

        assertEquals(this.info, page.getOtherInformation());
    }

}