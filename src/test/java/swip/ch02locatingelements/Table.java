package swip.ch02locatingelements;

import org.openqa.selenium.WebElement;

public interface Table extends WebElement {

    WebElement getBodyCell(int rowNumber, int columnNumber);

    WebElement getBodyCell(int rowNumber, String header);

    int getWidth();

    int getBodyHeight();

}
