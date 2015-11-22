package swip.ch15table;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import swip.ch14elements.framework.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Table<T> {

    private final Element table;
    private final Function<List<Element>, T> rowMapper;

    public Table(Element table, Function<List<Element>,T> rowMapper) {
        this.table = table;
        this.rowMapper = rowMapper;
    }

    public List<String> getHeaders() {
        List<String> headers = new ArrayList<>();
        for (WebElement th : table.findElements(By.tagName("th"))) {
            headers.add(th.getText());
        }
        return headers;
    }

    public List<T> getRows() {
        List<T> rows = new ArrayList<>();
        for (WebElement tr : table.findElements(By.tagName("tr"))) {

            List<Element> cells = new ArrayList<>();
            for (WebElement cell : tr.findElements(By.tagName("td"))) {
                cells.add(new Element(cell));
            }

            // skip header row, which will be empty
            if (!cells.isEmpty()) {
                rows.add(rowMapper.apply(cells));
            }
        }
        return rows;
    }

    public TableContents<T> getContents() {
        return new TableContents<>(getHeaders(), getRows());
    }
}