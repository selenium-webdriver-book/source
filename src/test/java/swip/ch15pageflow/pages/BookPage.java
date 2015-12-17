package swip.ch15pageflow.pages;

import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.Element;
import swip.ch15pageflow.locators.TagName;

import static swip.ch15pageflow.locators.TagName.INPUT;
import static swip.ch15pageflow.locators.Xpath.CART_BUTTON;

public class BookPage {

    private Predicate<Browser> colorBecomeWhite = new Predicate<Browser>() {
        @Override
        public boolean apply(Browser browser) {
            return cartButton().getCssValue("color").equals("rgba(255, 255, 255, 1)");
        }
    };

    private Browser browser;

    public BookPage(Browser browser) {
        this.browser = browser;
    }

    public void addToCart() {
        browser.findElements(INPUT).filter((e) -> e.getAttribute("value").equals("add to cart")).findFirst().get().click();
        new FluentWait<>(browser).until(colorBecomeWhite);
    }

    public void gotoCart() {
        cartButton().click();
    }

    public Element cartButton() {
        return browser.untilFound(CART_BUTTON);
    }

    public void secondAddToCart() {
        ((Element) browser.findElements(INPUT).filter((e) -> e.getAttribute("value").equals("add to cart")).toArray()[1]).click();
        new FluentWait<>(browser).until(colorBecomeWhite);
    }


}