package swip.po.d;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {
    private final WebElement queryInput;
    private final WebElement submitInput;

    public SearchPage(WebDriver driver) {
        if (!driver.getTitle().equals("Search")) {
            throw new IllegalArgumentException("page is not search page");
        }
        queryInput = driver.findElement(By.cssSelector("input[name='q']"));
        submitInput = driver.findElement(By.cssSelector("input[type='submit']"));
    }

    public void searchFor(String query) {
        queryInput.sendKeys(query);
        submitInput.click();
    }
}
