package swip.ch15table.city;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import swip.ch14elements.framework.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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

        Function<List<Element>, City> mapper = (cells) -> new City(
            Integer.parseInt(cells.get(0).getText()),
            cells.get(1).getText(),
            cells.get(2).getText()
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

    public CityTableContents getContents() {
        return new CityTableContents(getHeaders(), getRows());
    }
}