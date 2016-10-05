package swb.ch05pageobjects.pagefactory.whybad;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchForm {
    private static final By QUERY_SELECTOR = By.cssSelector("input[name='q']");
    private final WebDriver driver;

    public SearchForm(WebDriver driver) {
        this.driver = driver;
    }

    public void searchFor(String query) {
        driver.findElement(QUERY_SELECTOR)
                .sendKeys(query);
        driver.findElement(By.cssSelector("input[type='submit']"))
                .click();
    }

    public void clearQuery() {
        driver.findElement(QUERY_SELECTOR).clear();
    }
}
