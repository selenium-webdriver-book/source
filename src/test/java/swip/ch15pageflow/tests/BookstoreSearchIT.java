package swip.ch15pageflow.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch15pageflow.pages.BookstoreHomepage;
import swip.framework.Browser;
import swip.framework.BrowserRunner;

import javax.inject.Inject;

@RunWith(BrowserRunner.class)
public class BookstoreSearchIT {
    @Inject
    private Browser browser;

    @Test
    public void searchBook() {
        browser.get("/bookstore/");
        new BookstoreHomepage(browser) {{
            searchBook("Selenium WebDriver in Practice");
        }};
    }
}
