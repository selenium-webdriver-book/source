package swip.ch15table.person;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch14elements.framework.Browser;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class PersonTableIT {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private Browser browser;

    @Inject
    public void setWebDriver(WebDriver webDriver) {
        browser = new Browser(webDriver);
    }


    @Test
    public void testReadFromPersonTable() {

        browser.get("http://localhost:8080/people-table.html");

        PersonTable table = new PersonTable(
            browser.findElement(By.tagName("table"))
        );

        PersonTableContents actual = table.getContents();

        PersonTableContents expected = new PersonTableContents(
            Arrays.asList("Id", "First Name", "Last Name", "Age"),
            Arrays.asList(
                new Person(1, "Eve", "Jackson", 94)
                , new Person(2, "John", "Doe", 80)
                , new Person(3, "Alex", "Collins", 67)
                , new Person(4, "John", "Smith", 50)
            )
        );

        assertEquals(expected.describeDiff(actual), expected, actual);
    }

}
