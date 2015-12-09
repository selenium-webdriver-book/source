package swip.ch15pageflow;

import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.BrowserRunner;

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
        browser.get("http://www.manning.com");

        ManningHomepage homepage = new ManningHomepage(browser);
        homepage.searchBook("Selenium WebDriver in Practice");

        BookPage bookPage = new BookPage(browser);
        bookPage.addToCart();
        bookPage.gotoCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(browser);
        shoppingCartPage.setOtherInformation(otherInformation);

        assertEquals(this.otherInformation, shoppingCartPage.getOtherInformation());

    }

}