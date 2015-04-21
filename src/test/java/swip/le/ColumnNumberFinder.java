package swip.le;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

class ColumnNumberFinder {

    private final String headerText;

    ColumnNumberFinder(String headerText) {
        this.headerText = headerText;
    }

    public int find(SearchContext context) {
        int columnNumber = 1;
        for (; ; columnNumber++) {
            if (context
                    .findElement(By.cssSelector(String.format("th:nth-child(%d)", columnNumber)))
                    .getText().equals(headerText)) {
                return columnNumber;
            }
        }
    }
}
