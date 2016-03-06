package swip.ch15pageflow.pages.v2;


import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch15pageflow.framework.v2.Browser;
import swip.ch15pageflow.framework.v2.BrowserRunner;

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
