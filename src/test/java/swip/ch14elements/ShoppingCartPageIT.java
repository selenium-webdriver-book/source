package swip.ch13elements;

import com.google.common.base.Predicate;
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

    private OtherInformation otherInformation  = new OtherInformation(
        "no code",
        "joe@email.com",
        true,
        true,
        MailingOption.WEEKLY_NEWSLETTER,
        "no comments"
    );;

    private Predicate<Browser> colorBecomeWhite = (Browser b) -> cartButton().getCssValue("color").equals("rgba(255, 255, 255, 1)");

    @Inject
    public void setWebDriver(WebDriver webDriver) {
        browser = new Browser(webDriver);
    }

    @Test
    public void weShouldBeABleToCompleteOtherInformation() throws Exception {
        browser.get("https://www.manning.com/books/50-android-hacks");

        browser.findElement(By.className("btn-primary")).click();

        new FluentWait<>(browser).until(colorBecomeWhite) ;

        cartButton().click();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(browser);

        shoppingCartPage.setOtherInformation(otherInformation);

        assertEquals(this.otherInformation, shoppingCartPage.getOtherInformation());

    }

    private Element cartButton() {
        return browser.untilFound(By.xpath("//*[@id=\"primary-navbar\"]/ul[2]/li[2]/div/a"));
    }
}