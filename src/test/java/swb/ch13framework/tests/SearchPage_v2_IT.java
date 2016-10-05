package swb.ch13framework.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swb.ch05pageobjects.d.SearchPage;
import swb.ch13framework.v4.Browser;
import swb.ch13framework.v4.BrowserRunner;

import javax.inject.Inject;

@RunWith(BrowserRunner.class)
public class SearchPage_v2_IT {
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
