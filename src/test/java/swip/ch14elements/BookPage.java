package swip.ch14elements;

import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.FluentWait;
import swip.ch14elements.framework.Browser;
import swip.ch14elements.framework.Element;

import javax.annotation.Nullable;

public class BookPage {

    private Predicate<Browser> colorBecomeWhite =  new Predicate<Browser>() {
        @Override
        public boolean apply(@Nullable Browser browser) {
            return cartButton().getCssValue("color").equals("rgba(255, 255, 255, 1)");
        }
    };

    private Browser browser;

    public BookPage(Browser browser) {
        this.browser = browser;
    }

    public void addToCart() throws InterruptedException {
        browser.findElements(By.tagName("input")).stream().filter((e) -> e.getAttribute("value").equals("add to cart")).findFirst().get().click();
        new FluentWait<>(browser).until(colorBecomeWhite) ;
        Thread.sleep(5000);
    }

    public void gotoCart() {
        cartButton().click();
    }

    public Element cartButton() {
        return browser.untilFound(By.xpath("//*[@id=\"primary-navbar\"]/ul[2]/li[2]/a"));
    }

}