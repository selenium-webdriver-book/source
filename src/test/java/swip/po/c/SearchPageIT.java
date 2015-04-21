package swip.po.c;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;

@RunWith(SeleniumWebDriverRunner.class)
public class SearchPageIT {

    @Inject
    private WebDriver driver;

    @Test
    public void search() throws Exception {

        driver.get("http://localhost:8080/search.html");

        SearchPage searchForm = new SearchPage(driver);

        searchForm.searchFor("funny cats");
    }
}
