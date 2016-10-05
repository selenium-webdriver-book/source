package swb.ch12wrapping.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swb.ch12wrapping.table.SimpleTable;
import swb.ch12wrapping.table.Table;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class Table_v2_IT {
    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/users-table.html");
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
