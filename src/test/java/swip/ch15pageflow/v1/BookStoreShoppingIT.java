package swip.ch15pageflow.v1;


import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.BrowserRunner;
import swip.ch15pageflow.pages.BookstoreHomepage;

import javax.inject.Inject;

@RunWith(BrowserRunner.class)
public class BookStoreShoppingIT {
    @Inject
    private Browser browser;

    private BookstoreHomepage homePage;

    @Test
    public void invalidCardInfo() {
        homePage = new BookstoreHomepage(browser);
        homePage.searchBook("Selenium WebDriver in Practice");
    }
}
