package swip.ch15pageflow.v1;


import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.BrowserRunner;
import swip.ch15pageflow.pages.ManningHomepage;

import javax.inject.Inject;

@RunWith(BrowserRunner.class)
public class BookStoreShoppingIT {
    @Inject
    private Browser browser;

    private ManningHomepage homePage;

    @Test
    public void invalidCardInfo() {
        homePage = new ManningHomepage(browser);
        homePage.searchBook("Selenium WebDriver in Practice");
    }
}
