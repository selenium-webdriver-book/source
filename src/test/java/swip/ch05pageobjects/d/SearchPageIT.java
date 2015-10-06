package swip.ch05pageobjects.d;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;
import java.net.URI;

@RunWith(WebDriverRunner.class)
public class SearchPageIT {

    @Inject
    private WebDriver driver;
    @Inject
    private URI baseUrl;

    @Test
    public void search() throws Exception {

        driver.get(baseUrl + "/search.html");

        SearchPage searchForm = new SearchPage(driver);

        searchForm.searchFor("funny cats");
    }
}
