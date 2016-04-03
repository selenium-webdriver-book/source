package swip.ch16table.v1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import swip.ch15pageflow.framework.Element;
import swip.ch16table.domain.City;

import java.util.ArrayList;
import java.util.List;

import static swip.ch16table.mapper.CityMapper.MAPPER_LAMBDA;

public class CityTable {

    private final Element table;

    public CityTable(Element table) {
        this.table = table;
    }

    public List<String> getHeaders() {
        List<String> headers = new ArrayList<>();
        for (WebElement th : table.findElements(By.tagName("th"))) {
            headers.add(th.getText());
        }
        return headers;
    }

    public List<City> getRows() {
        List<City> rows = new ArrayList<>();

        for (WebElement tr : table.findElement(By.tagName("tbody"))
            .findElements(By.tagName("tr"))) {

            List<Element> cells = new ArrayList<>();
            for (WebElement cell : tr.findElements(By.tagName("td"))) {
                cells.add(new Element(cell));
            }

            rows.add(MAPPER_LAMBDA.apply(cells));

        }
        return rows;
    }

    public CityTableContents getContents() {
        return new CityTableContents(getHeaders(), getRows());
    }
}