package swip.ch15pageflow.v1.pages;


import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch15pageflow.v1.framework.Browser;
import swip.ch15pageflow.v1.framework.BrowserRunner;

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
