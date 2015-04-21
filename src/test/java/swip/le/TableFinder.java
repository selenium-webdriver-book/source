package swip.le;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

public class TableFinder implements WebElementFinder<SearchContext, Table> {
    private final By by;

    public TableFinder(By by) {
        this.by = by;
    }

    @Override
    public Table apply(SearchContext context) {
        return new SimpleTable(context.findElement(by));
    }
}
