package swip.ch16table.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import swip.framework.Element;
import swip.ch16table.domain.City;

import java.util.ArrayList;
import java.util.List;

import static swip.ch16table.mapper.CityMapper.MAPPER_LAMBDA;

public class CityTable extends AbstractTable {

    public CityTable(Element table) {
        super(table);
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