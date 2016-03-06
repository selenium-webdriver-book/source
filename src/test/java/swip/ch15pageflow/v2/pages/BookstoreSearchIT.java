package swip.ch15pageflow.v2.pages;


import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch15pageflow.v2.framework.Browser;
import swip.ch15pageflow.v2.framework.BrowserRunner;

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
