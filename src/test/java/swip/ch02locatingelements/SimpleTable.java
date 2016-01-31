package swip.ch02locatingelements;

import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

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
        ColumnNumberFinder finder = new ColumnNumberFinder(header);
        int columnNumber = finder.find(delegate.findElement(By.tagName("thead")));
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

    @Override
    public Rectangle getRect() {
        return null;
    }
}
