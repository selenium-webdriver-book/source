package swb.ch02locatingelements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

public class ColumnNumberFinder {

    private final SearchContext context;

    public ColumnNumberFinder(SearchContext context) {
        this.context = context;
    }

    public int find(String headerText) {
        for (int columnNumber = 1; ; columnNumber++) {
            if (context
                    .findElement(By.cssSelector(String.format("th:nth-child(%d)", columnNumber)))
                    .getText().equals(headerText)) {
                return columnNumber;
            }
        }
    }
}
