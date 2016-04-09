package swip.ch15pageflow.tests;


import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.BrowserRunner;
import swip.ch15pageflow.pages.v2.BookstoreHomepage;

import javax.inject.Inject;

@RunWith(BrowserRunner.class)
public class BookstoreSearchV2IT {
    @Inject
    private Browser browser;

    @Test
    public void invalidCardInfo() {
        new BookstoreHomepage(browser) {{
            searchBook("Selenium WebDriver in Practice");
        }};
    }
}
