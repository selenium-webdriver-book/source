package swb.ch05pageobjects.loadable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;

@RunWith(WebDriverRunner.class)
public class SearchFormIT {
    @Inject
    private WebDriver driver;

    @Test
    public void loadingSearchPage() throws Exception {
        SearchForm page = new SearchForm(driver).get();
        page.searchFor("funny cats");
    }
}