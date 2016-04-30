package swip.ch15pageflow.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch14elements.domain.MailingOption;
import swip.ch14elements.domain.OtherInformation;
import swip.ch15pageflow.domain.*;
import swip.ch15pageflow.pages.*;
import swip.framework.Browser;
import swip.framework.BrowserRunner;

import javax.inject.Inject;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static swip.locators.Id.ERROR_MESSAGE;

@RunWith(BrowserRunner.class)
public class BookstoreShoppingIT {

    public static final String EXPECTED_ERROR_MESSAGE =
        "The cardNumber must be between 19 and 19 characters long";
    public static final String EXPECTED_ORDER_NUMBER = "#00008";
    public static final String BOOKNAME = "Selenium WebDriver in Practice";

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

    private OtherInformation otherInformation = new OtherInformation(
        "no code",
        "joe@email.com",
        true,
        true,
        MailingOption.WEEKLY_NEWSLETTER,
        "no comments"
    );

    private CreditCard invalidCreditCard = new CreditCard(
        CreditCardType.MasterCard,
        "4111-1111-1111",
        "123",
        Month.DECEMBER, 2020);


    private CreditCard creditCard = new CreditCard(
        CreditCardType.MasterCard,
        "4111-1111-1111-1111",
        "123",
        Month.DECEMBER, 2020);

    @Before
    public void searchBookAndAddToCart() {
        browser.get("/bookstore/");
        BookstoreHomepage homePage = new BookstoreHomepage(browser);   //<1>
        homePage.searchBook(BOOKNAME);         //<2>

        BookListPage bookListPage = new BookListPage(browser);
        bookListPage.chooseBook(BOOKNAME);

        BookPage bookPage = new BookPage(browser);                    //<3>
        bookPage.addToCart();                                         //<4>
        bookPage.gotoCart();                                          //<5>
    }

    @Test
    public void invalidCreditCard() {
        ShoppingCartPage cartPage = new ShoppingCartPage(browser);                     //<6>
        cartPage.setBillingAddress(billingAddress);                   //<7>
        cartPage.setOtherInformation(otherInformation);               //<8>
        cartPage.setCreditCard(invalidCreditCard);          //<9>
        cartPage.confirm();                               //<10>

        assertEquals(EXPECTED_ERROR_MESSAGE, browser.getText(ERROR_MESSAGE));  //<11>
    }

    @Test
    public void purchaseSuccessful() {
        new ShoppingCartPage(browser) {{
            setBillingAddress(billingAddress);
            setOtherInformation(otherInformation);
            setCreditCard(creditCard);    //<1>
            confirm();
        }};
        new ConfirmationPage(browser) {{
            assertEquals(EXPECTED_ORDER_NUMBER, this.getOrderNumber());  //<2>
        }};
    }

}

