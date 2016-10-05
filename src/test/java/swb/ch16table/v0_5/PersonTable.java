package swb.ch16table.v0_5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import swb.framework.Element;

import java.util.ArrayList;
import java.util.List;

public class PersonTable {

    private final Element table;

    public PersonTable(Element table) {
        this.table = table;
    }

    public List<String> getHeaders() {   //<3>
        List<String> headers = new ArrayList<>();
        for (WebElement th : table.findElements(By.tagName("th"))) {  //<1>
            headers.add(th.getText());     //<2>
        }
        return headers;
    }
}