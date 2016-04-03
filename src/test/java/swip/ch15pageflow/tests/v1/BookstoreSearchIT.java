package swip.ch15pageflow.tests.v1;


import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.BrowserRunner;
import swip.ch15pageflow.pages.v1.BookstoreHomepage;

import javax.inject.Inject;

@RunWith(BrowserRunner.class)
public class BookstoreSearchIT {
    @Inject
    private Browser browser;

    private BookstoreHomepage homePage;

    @Test
    public void invalidCardInfo() {
        homePage = new BookstoreHomepage(browser);
        homePage.searchBook("Selenium WebDriver in Practice");
    }
}
