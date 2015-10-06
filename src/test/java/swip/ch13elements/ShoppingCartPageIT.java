package swip.ch13elements;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch13elements.framework.Browser;
import swip.framework.WebDriverRunner;
import org.openqa.selenium.support.ui.FluentWait;
import swip.ch13elements.framework.Element;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class ShoppingCartPageIT {
    private Browser browser;

    @Inject
    public void setWebDriver(WebDriver webDriver) {
        browser = new Browser(webDriver);
    }

    @Test
    public void weShouldBeABleToCompleteOtherInformation() throws Exception {
        browser.get("https://www.manning.com/books/50-android-hacks");

        browser.findElement(By.className("btn-primary")).click();

        new FluentWait<>(browser).until((Browser b) -> cartButton().getCssValue("color").equals("rgba(255, 255, 255, 1)")) ;

        cartButton().click();

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

    private Element cartButton() {
        return browser.untilFound(By.xpath("//*[@id=\"primary-navbar\"]/ul[2]/li[2]/div/a"));
    }
}