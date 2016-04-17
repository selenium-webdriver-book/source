package swip.ch15pageflow.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import swip.framework.Browser;
import swip.framework.BrowserRunner;
import swip.ch15pageflow.pages.v1.BookstoreHomepage;

import javax.inject.Inject;

@RunWith(BrowserRunner.class)
public class BookstoreSearch_v1_IT {
    @Inject
    private Browser browser;

    private BookstoreHomepage homePage;

    @Test
    public void searchBook() {
        homePage = new BookstoreHomepage(browser);
        homePage.searchBook("Selenium WebDriver in Practice");
    }
}
