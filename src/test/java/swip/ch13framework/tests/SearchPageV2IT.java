package swip.ch13framework.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch05pageobjects.d.SearchPage;
import swip.ch13framework.v6.Browser;
import swip.ch13framework.v6.BrowserRunner;

import javax.inject.Inject;

@RunWith(BrowserRunner.class)
public class SearchPageV2IT {
    @Inject
    private Browser browser;

    @Before
    public void gotoSearchPage() {
        browser.get("/search.html");
    }

    @Test
    public void search() throws Exception {
        SearchPage searchForm = new SearchPage(browser);
        searchForm.searchFor("funny cats");
    }
}
