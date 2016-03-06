package swip.ch15pageflow.pages.v1;


import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch15pageflow.framework.v1.Browser;
import swip.ch15pageflow.framework.v1.BrowserRunner;

import javax.inject.Inject;

@RunWith(BrowserRunner.class)
public class Bookstore_SearchIT {
    @Inject
    private Browser browser;

    private BookstoreHomepage homePage;

    @Test
    public void invalidCardInfo() {
        homePage = new BookstoreHomepage(browser);
        homePage.searchBook("Selenium WebDriver in Practice");
    }
}
