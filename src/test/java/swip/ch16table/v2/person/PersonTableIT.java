package swip.ch16table.v2.person;


import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.BrowserRunner;
import swip.ch16table.domain.Person;
import swip.ch16table.v2.table.Table;
import swip.ch16table.v2.table.TableContents;

import javax.inject.Inject;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static swip.ch15pageflow.locators.TagName.TABLE;
import static swip.ch16table.mapper.PersonMapper.MAPPER_NON_JAVA_8;

@RunWith(BrowserRunner.class)
public class PersonTableIT {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Inject
    private Browser browser;

    private static final TableContents<Person> EXPECTED = new TableContents<>(
        Arrays.asList("Id", "First Name", "Last Name", "Age"),
        Arrays.asList(
            new Person(1, "Eve", "Jackson", 94)
            , new Person(2, "John", "Doe", 80)
            , new Person(3, "Adam", "Johnson", 67)
            , new Person(4, "Jill", "Smith", 50)
        )
    );

    private static final TableContents<Person> EXPECTED_FAILURE = new TableContents<>(
        Arrays.asList("Id", "First Name", "Last Name", "Age"),
        Arrays.asList(
            new Person(1, "Eve", "Jackson", 94)
            , new Person(2, "John", "Doe", 80)
            , new Person(4, "Jill", "Smith", 50)
            , new Person(5, "Jack", "Clyde", 78)
        )
    );

    @Test
    public void testReadFromTable() {

        browser.get("/people-table.html");

        Table<Person> table = new Table<>(browser.untilFound(TABLE),
            cells ->
                new Person(Integer.parseInt(cells.get(0).getText()),
                    cells.get(1).getText(),
                    cells.get(2).getText(),
                    Integer.parseInt(cells.get(3).getText()))
        );

        TableContents<Person> actual = table.getContents();

        assertEquals(EXPECTED.describeDiff(actual), EXPECTED, actual);
    }

    @Test
    @Ignore("You can remove this to run it and check the output")
    public void missingExpectedValues() {

        browser.get("/people-table.html");

        Table<Person> table = new Table<>(browser.untilFound(TABLE), MAPPER_NON_JAVA_8);

        TableContents<Person> actual = table.getContents();

        assertEquals(EXPECTED_FAILURE.describeDiff(actual), EXPECTED_FAILURE, actual);
    }
}
