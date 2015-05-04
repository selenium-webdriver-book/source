package swip.ch06pageobjects.loadable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;

@RunWith(SeleniumWebDriverRunner.class)
public class SearchFormIT {
    @Inject
    private WebDriver driver;

    @Test
    public void loadingSearchPage() throws Exception {
        SearchForm page = new SearchForm(driver).get();
        page.searchFor("funny cats");
    }
}