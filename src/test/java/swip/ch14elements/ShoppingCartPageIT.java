package swip.ch14elements;

import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch14elements.domain.MailingOption;
import swip.ch14elements.domain.OtherInformation;
import swip.ch14elements.framework.Browser;
import swip.ch14elements.framework.BrowserRunner;
import swip.ch14elements.pages.BookPage;
import swip.ch14elements.pages.ShoppingCartPage;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class ShoppingCartPageIT {
    @Inject private Browser browser;

    private OtherInformation otherInformation  = new OtherInformation(
        "no code",
        "joe@email.com",
        true,
        true,
        MailingOption.WEEKLY_NEWSLETTER,
        "no comments"
    );


    @Test
    public void weShouldBeABleToCompleteOtherInformation() throws Exception {
        browser.get("/bookstore/books/selenium-webdriver-in-practice");

        BookPage bookPage = new BookPage(browser);
        bookPage.addToCart();
        bookPage.gotoCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(browser);
        shoppingCartPage.setOtherInformation(otherInformation);

        assertEquals(this.otherInformation, shoppingCartPage.getOtherInformation());

    }

}