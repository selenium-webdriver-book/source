package swip.ch05pageobjects.pagefactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

@RunWith(WebDriverRunner.class)
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