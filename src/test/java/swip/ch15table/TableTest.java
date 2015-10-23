package swip.ch14table;


import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.stream.Stream;

import static com.google.common.collect.Sets.newHashSet;
import static swip.ch14table.Locators.element;

@RunWith(WebDriverRunner.class)
public class TableTest {

    private Browser browser;
    @Inject
    public void setWebDriver(WebDriver webDriver) {
        browser = new Browser(webDriver);
    }

    private TableContents<Person> expected = new TableContents<>(
            newHashSet("Number", "First Name", "Last Name", "Points"),
            Sets.<Person>newHashSet(
                    new Person(4, "Jill", "Smith", 50)
                    , new Person(3, "Adam", "Johnson", 67)
                    , new Person(2, "John", "Doe", 80)
                    , new Person(1, "Eve", "Jackson", 94)
            )
    );

    @Test
    public void testReadFromTable() {

        browser.get("http://www.w3schools.com/html/html_tables.asp");
        Locator<Browser, Element> locator =
                Locators.<Browser>element(Xpath.TABLE_CONTAINER)
                        .andThen(element(TagName.TABLE));
        Locator<Stream<Element>, Person> mapper = (stream) -> {
            Iterator<String> iterator = stream.map(Element::getText).iterator();
            return new Person(StringToInt.PARSE_INT.locate(iterator.next()),
                    iterator.next(),
                    iterator.next(),
                    StringToInt.PARSE_INT.locate(iterator.next()));
        };
        Table<Person, Browser> table = new Table<>(browser, locator, mapper);

        assertTrue(expected, expected.equals(table.getContents()));
        browser.close();
    }

    private void assertTrue(Object diff, boolean pass) {
        if (!pass) {
            Assert.assertTrue(diff.toString(), pass);
        }
    }

}
