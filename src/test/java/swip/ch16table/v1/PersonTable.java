package swip.ch16table.v1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import swip.framework.Element;
import swip.ch16table.domain.Person;
import swip.ch16table.mapper.PersonMapper;

import java.util.ArrayList;
import java.util.List;

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

        for (WebElement tr : table.findElement(By.tagName("tbody"))
            .findElements(By.tagName("tr"))) {

            List<Element> cells = new ArrayList<>();
            for (WebElement cell : tr.findElements(By.tagName("td"))) {
                cells.add(new Element(cell));
            }

            rows.add(PersonMapper.MAPPER_NON_JAVA_8.apply(cells));

        }
        return rows;
    }

    public PersonTableContents getContents() {
        return new PersonTableContents(getHeaders(), getRows());
    }
}