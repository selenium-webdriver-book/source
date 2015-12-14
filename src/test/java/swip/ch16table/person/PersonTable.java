package swip.ch16table.person;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import swip.ch14elements.framework.Element;

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

    public List<Person> getRows() {
        List<Person> rows = new ArrayList<>();

        Function<List<Element>, Person> mapper = (cells) -> new Person(
            Integer.parseInt(cells.get(0).getText()),
            cells.get(1).getText(),
            cells.get(2).getText(),
            Integer.parseInt(cells.get(3).getText())
        );

        for (WebElement tr : table.findElements(By.tagName("tr"))) {

            List<Element> cells = new ArrayList<>();
            for (WebElement cell : tr.findElements(By.tagName("td"))) {
                cells.add(new Element(cell));
            }

            // skip header row, which will be empty
            if (!cells.isEmpty()) {
                rows.add(mapper.apply(cells));
            }
        }
        return rows;
    }

    public PersonTableContents getContents() {
        return new PersonTableContents(getHeaders(), getRows());
    }
}