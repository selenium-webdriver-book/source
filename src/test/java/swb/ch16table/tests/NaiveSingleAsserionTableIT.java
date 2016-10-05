package swb.ch16table.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swb.ch12wrapping.table.SimpleTable;
import swb.ch12wrapping.table.Table;
import swb.framework.Browser;
import swb.framework.BrowserRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static swb.locators.TagName.TABLE;

@RunWith(BrowserRunner.class)
public class NaiveSingleAsserionTableIT {

    @Inject
    private Browser browser;
    private Table table;

    @Before
    public void readTable() {
        browser.get("/people-table.html");
        table = new SimpleTable(browser.await(TABLE));
    }

    @Test
    public void header1ShouldBeId() {
        assertEquals("Id", table.getHeader(1).getText());
    }

    @Test
    public void header2ShouldBeFirstName() {
        assertEquals("First Name", table.getHeader(2).getText());
    }

    @Test
    public void row1Column1ShouldBe1() {
        assertEquals("1", table.getBodyCell(1, 1).getText());
    }

    @Test
    public void row1Column2ShouldBeEve() {
        assertEquals("Eve", table.getBodyCell(1, 2).getText());
    }

}
