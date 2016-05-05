package swip.ch16table.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import swip.framework.Element;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTable {
    protected Element table;

    public AbstractTable(Element table) {
        this.table = table;
    }

    public List<String> getHeaders() {
        List<String> headers = new ArrayList<>();
        for (WebElement th : table.findElements(By.tagName("th"))) {
            headers.add(th.getText());
        }
        return headers;
    }


}
