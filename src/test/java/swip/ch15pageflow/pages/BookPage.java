package swip.ch15pageflow.pages;

import com.google.common.base.Predicate;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.FluentWait;
import swip.ch15pageflow.framework.ExplicitWait;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.Element;

import java.util.function.Function;

import static swip.ch15pageflow.locators.TagName.INPUT;
import static swip.ch15pageflow.locators.Xpath.CART_BUTTON;

public class BookPage {

    private Browser browser;

    public BookPage(Browser browser) {
        this.browser = browser;
    }

    public void addToCart() {
        browser.findElements(INPUT).filter((e) -> e.getAttribute("value").equals("add to cart")).findFirst().get().click();
    }

    public void gotoCart() {
        cartButton().click();
    }

    public Element cartButton() {
        return browser.untilFound(CART_BUTTON);
    }

}