package swb.ch05pageobjects.d;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {
    private final WebElement queryInput;
    private final WebElement submitInput;

    public SearchPage(WebDriver driver) {
        String pageTitle = driver.getTitle();
        if (!pageTitle.equals("Search")) {
            throw new IllegalArgumentException(String.format("page is not search page, it has un-expected title %s", pageTitle));
        }
        queryInput = driver.findElement(By.cssSelector("input[name='q']"));
        submitInput = driver.findElement(By.cssSelector("input[type='submit']"));
    }

    public void searchFor(String query) {
        queryInput.sendKeys(query);
        submitInput.click();
    }
}
