package swip.ch05pageobjects.a;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;
import java.net.URI;

@RunWith(WebDriverRunner.class)
public class SearchFormIT {

    @Inject
    private WebDriver driver;
    @Inject
    private URI baseUrl;

    @Test
    public void search() throws Exception {

        driver.get(baseUrl + "/search.html");

        SearchForm searchForm = new SearchForm(driver);

        searchForm.setQuery("funny cats");
        searchForm.submit();
    }
}
