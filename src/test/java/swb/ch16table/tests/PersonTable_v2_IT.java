package swb.ch16table.tests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import swb.framework.Browser;
import swb.framework.BrowserRunner;
import swb.ch16table.domain.Person;
import swb.ch16table.v3.Table;
import swb.ch16table.v3.TableContents;

import javax.inject.Inject;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static swb.locators.TagName.TABLE;
import static swb.ch16table.mapper.PersonMapper.MAPPER_NON_JAVA_8;

@RunWith(BrowserRunner.class)
public class PersonTable_v2_IT {

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

    @Before
    public void gotoSite() {
        browser.get("/people-table.html");
    }

    @Test
    public void testReadFromTable() {

        Table<Person> table = new Table<>(browser.await(TABLE),
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

        Table<Person> table = new Table<>(browser.await(TABLE), MAPPER_NON_JAVA_8);

        TableContents<Person> actual = table.getContents();

        assertEquals(EXPECTED_FAILURE.describeDiff(actual), EXPECTED_FAILURE, actual);
    }
}
