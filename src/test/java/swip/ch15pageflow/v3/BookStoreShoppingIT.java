package swip.ch15pageflow.v3;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import swip.ch15pageflow.domain.*;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.BrowserRunner;
import swip.ch15pageflow.pages.BookPage;
import swip.ch15pageflow.pages.ErrorMessages;
import swip.ch15pageflow.pages.ManningHomepage;
import swip.ch15pageflow.pages.ShoppingCartPage;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class BookStoreShoppingIT {
    List<String> list1 = Arrays.asList("You don't seem to have supplied your billing Phone.");

    @Inject
    private Browser browser;

    private Address billingAddress = new Address(
        "1111 Mountain Dr",
        "14052014",
        "Edison",
        "08820",
        UnitedStates.New_Jersey,
        Countries.United_States,
        "Sanjay",
        "Rao");
    private CreditCard creditCard = new CreditCard(
        CreditCardType.MasterCard,
        "4111-1111-1111-1111",
        "123",
        Month.DECEMBER, 2020);
    private OtherInformation otherInformation = new OtherInformation(
        "no code",
        "joe@email.com",
        true,
        true,
        MailingOption.WEEKLY_NEWSLETTER,
        "no comments"
    );

    private ManningHomepage homePage;


    @Test
    public void invalidCardInfo() {
        homePage = new ManningHomepage(browser);

        homePage.searchBook2("Selenium WebDriver in Practice");

        BookPage bookPage = new BookPage(browser);
        bookPage.addToCart();
        bookPage.gotoCart();

        ShoppingCartPage cartPage = new ShoppingCartPage(browser);
        cartPage.setBillingAddress(billingAddress);
        cartPage.setCreditCard(creditCard);
        cartPage.setOtherInformation(otherInformation);
        cartPage.continues();

        assertEquals("Expiration Date: 12, 2020",browser.untilFound(() -> By.id("expirationDate")).getText());

    }

}
