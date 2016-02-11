package swip.ch15pageflow.v3;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import swip.ch15pageflow.domain.*;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.BrowserRunner;
import swip.ch15pageflow.pages.BookPage;
import swip.ch15pageflow.pages.BookstoreHomepage;
import swip.ch15pageflow.pages.ShoppingCartPage;

import javax.inject.Inject;
import java.time.Month;

import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class BookStoreShoppingIT {

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

    private CreditCard invalidCreditCard = new CreditCard(
        CreditCardType.MasterCard,
        "4111-1111-1111",
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

    private BookstoreHomepage homePage;

    @Before
    public void addToCart() {
        homePage = new BookstoreHomepage(browser);

        homePage.searchBook2("Selenium WebDriver in Practice");

        BookPage bookPage = new BookPage(browser);
        bookPage.addToCart();
        bookPage.gotoCart();

    }

    @Test
    public void purchaseSuccessful() {

        ShoppingCartPage cartPage = new ShoppingCartPage(browser);
        cartPage.setBillingAddress(billingAddress);
        cartPage.setCreditCard(creditCard);
        cartPage.setOtherInformation(otherInformation);
        cartPage.continues();

        assertEquals("Order number #00008.",browser.untilFound(() -> By.id("orderNumber")).getText());

    }

    @Test
    public void invalidCreditCard() {

        ShoppingCartPage cartPage = new ShoppingCartPage(browser);
        cartPage.setBillingAddress(billingAddress);
        cartPage.setCreditCard(invalidCreditCard);
        cartPage.setOtherInformation(otherInformation);
        cartPage.continues();

        assertEquals("The cardNumber must be between 19 and 19 characters long",browser.untilFound(() -> By.id("cardNumber.errors")).getText());

    }

}

