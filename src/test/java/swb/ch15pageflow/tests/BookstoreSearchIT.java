package swb.ch15pageflow.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import swb.ch15pageflow.pages.BookstoreHomepage;
import swb.framework.Browser;
import swb.framework.BrowserRunner;

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
