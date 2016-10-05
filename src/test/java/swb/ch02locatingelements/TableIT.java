package swb.ch02locatingelements;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swb.ch12wrapping.table.ColumnNumberFinder;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class TableIT {
    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/users-table.html");
    }

    @Test
    public void findCell() throws Exception {
        int columnNumber = 1;
        while (!driver
                .findElement(By.cssSelector(String.format("table#users-table th:nth-child(%d)", columnNumber)))
                .getText().equals("Name")) {
            columnNumber++;
        }
        assertEquals("John Doe",
                driver.findElement(By.cssSelector(String.format("table#users-table tbody tr:nth-child(1) td:nth-child(%d)", columnNumber)))
                        .getText());

    }

    @Test
    public void columnNumberLocator() throws Exception {

        int columnNumber = new ColumnNumberFinder(driver.findElement(By.cssSelector("table#users-table")))
                .find("Name");

        driver.findElement(By.cssSelector(String.format("table#users-table tbody tr:nth-child(1) td:nth-child(%d)", columnNumber)));
    }

    @Test
    public void classicCssSelector() throws Exception {
        assertEquals("John Doe",
                driver.findElement(By.cssSelector("table#users-table tbody tr:nth-child(1) td:nth-child(3)"))
                        .getText());
    }

    @Test
    public void classicXpathQuery() throws Exception {
        assertEquals("John Doe",
                driver.findElement(By.xpath("//table[@id='users-table']/tbody/tr[1]/td[3]"))
                        .getText());
    }

}
