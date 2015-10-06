package swip.ch02locatingelements;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch07managingwebdriver.injecting.Config;
import swip.ch07managingwebdriver.injecting.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
@Config(exclude = "browserName=htmlunit")
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

        int columnNumber = new ColumnNumberFinder("Name")
                .find(driver.findElement(By.cssSelector("table#users-table")));

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

    @Test
    public void tableEncapsulation() throws Exception {

        Table table = new SimpleTable(driver.findElement(By.id("users-table")));

        assertEquals("John Doe", table.getBodyCell(1, 3).getText());
        assertEquals("John Doe", table.getBodyCell(1, "Name").getText());
        assertEquals(3, table.getWidth());
        assertEquals(2, table.getBodyHeight());

    }
}
