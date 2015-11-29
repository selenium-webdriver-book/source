package swip.ch14elements;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swip.ch14elements.framework.Browser;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class ShoppingCartPageIT {
    private Browser browser;

    private OtherInformation otherInformation  = new OtherInformation(
        "no code",
        "joe@email.com",
        true,
        true,
        MailingOption.WEEKLY_NEWSLETTER,
        "no comments"
    );


    @Inject
    public void setWebDriver(WebDriver webDriver) {
        browser = new Browser(webDriver);
    }

    @Test
    public void weShouldBeABleToCompleteOtherInformation() throws Exception {
        browser.get("https://www.manning.com/books/selenium-webdriver-in-practice");

        BookPage bookPage = new BookPage(browser);
        bookPage.addToCart();
        bookPage.gotoCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(browser);
        shoppingCartPage.setOtherInformation(otherInformation);

        assertEquals(this.otherInformation, shoppingCartPage.getOtherInformation());

    }


}