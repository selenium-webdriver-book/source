package swip.ch02locatingelements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.BiPredicate;

public class SimpleTable extends DelegatingWebElement implements Table {

    public SimpleTable(WebElement delegate) {
        super(delegate);
    }

    @Override
    public WebElement getBodyCell(int rowNumber, int columnNumber) {
        return delegate
                .findElement(By.tagName("tbody"))
                .findElement(TdBy.cellLocation(rowNumber, columnNumber));
    }

    @Override
    public WebElement getBodyCell(int rowNumber, String header) {
        ColumnNumberFinder finder = new ColumnNumberFinder(header); // # create an object to find the column
        int columnNumber = finder.find(delegate.findElement(By.tagName("thead"))); // # find the column
        return getBodyCell(rowNumber, columnNumber);
    }

    @Override
    public WebElement getBodyCell(BiPredicate<WebElement, CellLocation> cellMatcher) {

        List<WebElement> bodyRows = delegate.findElements(By.cssSelector("tbody tr"));

        for (int rowIndex = 0; rowIndex < bodyRows.size(); rowIndex++) { // # perform an indexed iteration over the rows

            WebElement bodyRow = bodyRows.get(rowIndex);

            List<WebElement> cells = bodyRow.findElements(By.tagName("td"));

            for (int columnIndex = 0; columnIndex < cells.size(); columnIndex++) { // # perform an indexed iteration over the cells

                WebElement cell = cells.get(columnIndex);
                CellLocation cellLocation = new CellLocation(rowIndex + 1, columnIndex + 1);

                if (cellMatcher.test(cell, cellLocation)) { // # if the cell matches, then return it
                    return cell;
                }
            }
        }

        throw new NoSuchElementException("cannot find cell");
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
