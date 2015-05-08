package swip.ch02locatingelements;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(SeleniumWebDriverRunner.class)
public class TableFinderIT {
    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/users-table.html");
    }

    @Test
    public void testTableFinder() throws Exception {

        WebElementFinder<SearchContext, WebElement> containerFinder
                = new WebElementByFinder(By.className("container"));

        WebElementFinder<SearchContext, Table> tableFinder
                = new TableFinder(By.id("users-table"));

        Table table = containerFinder
                .andThen(tableFinder)
                .apply(driver);

        assertEquals("Jane Smith", table.getBodyCell(2, 3).getText());
    }
}
