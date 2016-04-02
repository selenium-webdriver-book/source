package swip.ch16table.v2.person;


import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import swip.ch15pageflow.v2.framework.Browser;
import swip.ch15pageflow.v2.framework.BrowserRunner;
import swip.ch15pageflow.v2.framework.Element;
import swip.ch16table.domain.Person;
import swip.ch16table.v2.table.Table;
import swip.ch16table.v2.table.TableContents;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static swip.ch15pageflow.locators.TagName.TABLE;

@RunWith(BrowserRunner.class)
public class PersonTableIT {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Inject private Browser browser;


    private final Function<List<Element>, Person> mapper = (cells) -> new Person(
        Integer.parseInt(cells.get(0).getText()),
        cells.get(1).getText(),
        cells.get(2).getText(),
        Integer.parseInt(cells.get(3).getText())
    );

    Function<List<Element>, Person> mapperNonJava8 = new Function<List<Element>, Person>() {
        @Override
        public Person apply(List<Element> cells) {
            return  new Person(
                Integer.parseInt(cells.get(0).getText()),
                cells.get(1).getText(),
                cells.get(2).getText(),
                Integer.parseInt(cells.get(3).getText())
            );
        }
    };

    @Test
    public void testReadFromTable() {

        browser.get("/people-table.html");

        Table<Person> table = new Table<>(
                browser.untilFound(TABLE),
                mapper
        );

        TableContents<Person> actual = table.getContents();

        TableContents<Person> expected = new TableContents<>(
                Arrays.asList("Id", "First Name", "Last Name", "Age"),
                Arrays.asList(
                        new Person(1, "Eve", "Jackson", 94)
                        , new Person(2, "John", "Doe", 80)
                        , new Person(3, "Adam", "Johnson", 67)
                        , new Person(4, "Jill", "Smith", 50)
                )
        );

        assertEquals(expected.describeDiff(actual), expected, actual);
    }

    @Test
    @Ignore("fails in both FF and Chrome, you can remove this to run ir and check the output")
    public void missingExpectedValues() {

        browser.get("/people-table.html");

        Table<Person> table = new Table<>(
                browser.untilFound(TABLE),
                mapperNonJava8
        );

        TableContents<Person> actual = table.getContents();

        TableContents<Person> expected = new TableContents<>(
                Arrays.asList("Id", "First Name", "Last Name", "Age"),
                Arrays.asList(
                        new Person(1, "Eve", "Jackson", 94)
                        , new Person(2, "John", "Doe", 80)
                        , new Person(4, "Jill", "Smith", 50)
                        , new Person(5, "Jack", "Clyde", 78)
                )
        );

        assertEquals(expected.describeDiff(actual), expected, actual);
    }
}
