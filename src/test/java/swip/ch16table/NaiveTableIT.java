package swip.ch16table;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import swip.ch14elements.framework.Browser;
import swip.ch14elements.framework.BrowserRunner;
import swip.ch14elements.framework.Element;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class NaiveTableIT {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Inject private Browser browser;

    @Test
    public void testReadFromTable() {

        browser.get("http://localhost:8080/people-table.html");

        Element table = browser.findElement(By.tagName("table"));

        List<WebElement> headers = table.findElements(By.tagName("tr"))
            .get(0).findElements(By.tagName("th"));

        assertEquals("Id", headers.get(0).getText());
        assertEquals("First Name", headers.get(1).getText());
        assertEquals("Last Name", headers.get(2).getText());
        assertEquals("Age", headers.get(3).getText());

        List<WebElement> row1 = table.findElements(By.tagName("tr"))
            .get(1).findElements(By.tagName("td"));
        assertEquals("1", row1.get(0).getText());
        assertEquals("Eve", row1.get(1).getText());
        assertEquals("Jackson", row1.get(2).getText());
        assertEquals("94", row1.get(3).getText());

        List<WebElement> row2 = table.findElements(By.tagName("tr"))
            .get(2).findElements(By.tagName("td"));
        assertEquals("2", row2.get(0).getText());
        assertEquals("John", row2.get(1).getText());
        assertEquals("Doe", row2.get(2).getText());
        assertEquals("80", row2.get(3).getText());

        List<WebElement> row3 = table.findElements(By.tagName("tr"))
            .get(3).findElements(By.tagName("td"));
        assertEquals("3", row3.get(0).getText());
        assertEquals("Adam", row3.get(1).getText());
        assertEquals("Johnson", row3.get(2).getText());
        assertEquals("67", row3.get(3).getText());

        List<WebElement> row4 = table.findElements(By.tagName("tr"))
            .get(4).findElements(By.tagName("td"));
        assertEquals("4", row4.get(0).getText());
        assertEquals("Jill", row4.get(1).getText());
        assertEquals("Smith", row4.get(2).getText());
        assertEquals("50", row4.get(3).getText());

    }

}
