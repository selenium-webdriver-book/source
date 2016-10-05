package swb.ch12wrapping.table;

import org.openqa.selenium.WebElement;

public interface Table extends WebElement {

    WebElement getHeader(int columnNumber);

    WebElement getHeader(String header);

    WebElement getBodyCell(int rowNumber, int columnNumber);

    WebElement getBodyCell(int rowNumber, String header);

    int getWidth();

    int getBodyHeight();

}
