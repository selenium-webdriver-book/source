package swip.ch06pageobjects.pagefactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;

@RunWith(SeleniumWebDriverRunner.class)
public class SearchFormIT {
    @Inject
    private WebDriver driver;

    @Test
    public void pageFactory() throws Exception {
        driver.get("/search.html");
        SearchForm searchForm = PageFactory.initElements(driver, SearchForm.class);
        searchForm.searchFor("funny cats");
    }
}