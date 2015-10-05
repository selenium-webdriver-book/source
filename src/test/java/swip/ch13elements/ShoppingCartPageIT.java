package swip.ch13elements;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch07managingwebdriver.WebDriverRunner;
import swip.ch13elements.framework.Browser;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class ShoppingCartPageIT {
    private Browser browser;

    @Inject
    public void setWebDriver(WebDriver webDriver) {
        browser = new Browser(webDriver);
    }

    @Ignore("pending fix")
    @Test
    public void weShouldBeABleToCompleteOtherInformation() throws Exception {
        browser.get("https://www.manning.com/books/50-android-hacks");

        browser.findElement(By.className("btn-primary")).click();
        browser.get("http://ycart.manning.com/");

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(browser);

        shoppingCartPage.setOtherInformation(new OtherInformation(
                "no code",
                "joe@email.com",
                true,
                true,
                MailingOption.WEEKLY_NEWSLETTER,
                "no comments"
        ));


        OtherInformation otherInformation = shoppingCartPage.getOtherInformation();

        assertEquals("no code", otherInformation.couponCode);
        assertEquals("joe@email.com", otherInformation.email);
        assertEquals(true, otherInformation.sendOrdersToEmail);
        assertEquals(true, otherInformation.sendRatingEmail);
        assertEquals(MailingOption.WEEKLY_NEWSLETTER, otherInformation.mailingOption);
        assertEquals("no comments", otherInformation.comments);
    }
}