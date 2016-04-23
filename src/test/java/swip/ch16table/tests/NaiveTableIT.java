package swip.ch16table.tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import swip.ch12wrapping.table.SimpleTable;
import swip.ch12wrapping.table.Table;
import swip.framework.Browser;
import swip.framework.BrowserRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static swip.locators.TagName.TABLE;

@RunWith(BrowserRunner.class)
public class NaiveTableIT {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Inject
    private Browser browser;

    @Test
    public void testReadFromTable() {

        browser.get("/people-table.html");

        Table table = new SimpleTable(browser.untilFound(TABLE));

        assertEquals("Id", table.getHeader(1).getText());
        assertEquals("First Name", table.getHeader(2).getText());
        assertEquals("Last Name", table.getHeader(3).getText());
        assertEquals("Age", table.getHeader(4).getText());

        assertEquals("1", table.getBodyCell(1, 1).getText());
        assertEquals("Eve", table.getBodyCell(1, 2).getText());
        assertEquals("Jackson", table.getBodyCell(1, 3).getText());
        assertEquals("94", table.getBodyCell(1, 4).getText());

        assertEquals("2", table.getBodyCell(2, 1).getText());
        assertEquals("John", table.getBodyCell(2, 2).getText());
        assertEquals("Doe", table.getBodyCell(2, 3).getText());
        assertEquals("80", table.getBodyCell(2, 4).getText());

        assertEquals("3", table.getBodyCell(3, 1).getText());
        assertEquals("Adam", table.getBodyCell(3, 2).getText());
        assertEquals("Johnson", table.getBodyCell(3, 3).getText());
        assertEquals("67", table.getBodyCell(3, 4).getText());

        assertEquals("4", table.getBodyCell(4, 1).getText());
        assertEquals("Jill", table.getBodyCell(4, 2).getText());
        assertEquals("Smith", table.getBodyCell(4, 3).getText());
        assertEquals("50", table.getBodyCell(4, 4).getText());

    }

}
