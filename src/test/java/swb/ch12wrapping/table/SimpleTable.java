package swb.ch12wrapping.table;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import swb.ch02locatingelements.DelegatingWebElement;

public class SimpleTable extends DelegatingWebElement implements Table {

    private final ColumnNumberFinder columnNumberFinder;

    public SimpleTable(WebElement delegate) {
        super(delegate);
        columnNumberFinder = new ColumnNumberFinder(
                delegate.findElement(By.tagName("thead")));
    }

    @Override
    public WebElement getHeader(int columnNumber) {
        return delegate
            .findElement(By.tagName("thead"))
            .findElement(TdBy.tableHeader(columnNumber));
    }

    @Override
    public WebElement getHeader(String header) {
        int columnNumber = columnNumberFinder.find(header);
        return getHeader(columnNumber);
    }

    @Override
    public WebElement getBodyCell(int rowNumber, int columnNumber) {
        return delegate
                .findElement(By.tagName("tbody"))
                .findElement(TdBy.cellLocation(rowNumber, columnNumber));
    }

    @Override
    public WebElement getBodyCell(int rowNumber, String header) {
        int columnNumber = columnNumberFinder.find(header);
        return getBodyCell(rowNumber, columnNumber);
    }

    @Override
    public int getWidth() {
        return delegate.findElements(By.tagName("th")).size();
    }

    @Override
    public int getBodyHeight() {
        return delegate.findElements(By.cssSelector("tbody tr")).size();
    }
}
