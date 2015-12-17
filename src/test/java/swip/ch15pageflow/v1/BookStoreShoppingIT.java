package swip.ch15pageflow.v1;


import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch15pageflow.domain.*;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.BrowserRunner;
import swip.ch15pageflow.pages.BookPage;
import swip.ch15pageflow.pages.ErrorMessages;
import swip.ch15pageflow.pages.ManningHomepage;
import swip.ch15pageflow.pages.ShoppingCartPage;

import javax.inject.Inject;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class BookStoreShoppingIT {
    @Inject
    private Browser browser;

    private ManningHomepage homePage;


    @Test
    public void invalidCardInfoNormalWay() {
        homePage = new ManningHomepage(browser);
        homePage.searchBook("Selenium WebDriver in Practice");
    }

}
