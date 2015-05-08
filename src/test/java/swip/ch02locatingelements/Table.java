package swip.ch02locatingelements;

import org.openqa.selenium.WebElement;

import java.util.function.BiPredicate;

public interface Table extends WebElement {

    WebElement getBodyCell(int rowNumber, int columnNumber);

    WebElement getBodyCell(int rowNumber, String header);

    WebElement getBodyCell(BiPredicate<WebElement, CellLocation> cellMatcher);

    int getWidth();

    int getBodyHeight();

}
