package swip.le;

import org.openqa.selenium.By;

public final class TdBy {
    private TdBy() {
    }

    public static By cellLocation(int rowNumber, int columnNumber) {
        return By.xpath(String.format("//tr[%d]/td[%d]", rowNumber, columnNumber));
        // not supported by htmldriver
        // return By.cssSelector(String.format("tr:nth-child(%d) td:nth-child(%d)", rowNumber, columnNumber));
    }
}
