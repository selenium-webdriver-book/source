package swip.ch16table.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch12wrapping.table.SimpleTable;
import swip.ch12wrapping.table.Table;
import swip.framework.Browser;
import swip.framework.BrowserRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static swip.locators.TagName.TABLE;

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
