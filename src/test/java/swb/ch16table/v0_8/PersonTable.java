package swb.ch16table.v0_8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import swb.framework.Element;
import swb.ch16table.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PersonTable {

    private final Element table;

    public PersonTable(Element table) {
        this.table = table;
    }

    public List<String> getHeaders() {
        List<String> headers = new ArrayList<>();
        for (WebElement th : table.findElements(By.tagName("th"))) {
            headers.add(th.getText());
        }
        return headers;
    }

    private static final Function<List<Element>, Person> MAPPER_NON_JAVA_8
        = new Function<List<Element>, Person>() {
        @Override
        public Person apply(List<Element> cells) {
            return new Person(
                Integer.parseInt(cells.get(0).getText()),
                cells.get(1).getText(),
                cells.get(2).getText(),
                Integer.parseInt(cells.get(3).getText())
            );
        }
    };

    public List<Person> getRows() {
        List<Person> rows = new ArrayList<>();

        for (WebElement tr : table.findElement(By.tagName("tbody"))
            .findElements(By.tagName("tr"))) {

            List<Element> cells = new ArrayList<>();
            for (WebElement cell : tr.findElements(By.tagName("td"))) {
                cells.add(new Element(cell));
            }

            rows.add(MAPPER_NON_JAVA_8.apply(cells));

        }
        return rows;
    }
}