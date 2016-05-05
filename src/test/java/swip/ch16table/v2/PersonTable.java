package swip.ch16table.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import swip.ch16table.domain.Person;
import swip.ch16table.mapper.PersonMapper;
import swip.framework.Element;

import java.util.ArrayList;
import java.util.List;

public class PersonTable extends AbstractTable{

    public PersonTable(Element table) {
        super(table);
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