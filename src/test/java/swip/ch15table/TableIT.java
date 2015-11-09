package swip.ch15table;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch14elements.framework.Browser;
import swip.ch14elements.framework.Element;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
// named class consistently with other integration tests
public class TableIT {

    private Browser browser;

    @Inject
    public void setWebDriver(WebDriver webDriver) {
        browser = new Browser(webDriver);
    }

    @Test
    public void testReadFromTable() {

        // avoid using external resources, may change
        browser.get("http://localhost:8080/people-table.html");

        // ok to use Java 8 here, but
        Function<List<Element>, Person> mapper = (cells) -> new Person(
                Integer.parseInt(cells.get(0).getText()),
                cells.get(1).getText(),
                cells.get(2).getText(),
                Integer.parseInt(cells.get(3).getText())
        );
        Table<Person> table = new Table<>(
                browser.findElement(By.tagName("table")),
                mapper
        );

        TableContents<Person> actual = table.getContents();

        TableContents<Person> expected = new TableContents<>(
                Arrays.asList("Number", "First Name", "Last Name", "Age"),
                // Arrays.asList well known core Java
                Arrays.asList(
                        new Person(4, "Jill", "Smith", 50)
                        , new Person(3, "Adam", "Johnson", 67)
                        , new Person(2, "John", "Doe", 80)
                        , new Person(1, "Eve", "Jackson", 94)
                )
        );

        assertEquals(expected.describeDiff(actual), expected, actual);
    }
}
