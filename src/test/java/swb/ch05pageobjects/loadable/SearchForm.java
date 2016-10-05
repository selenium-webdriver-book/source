package swb.ch05pageobjects.loadable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.junit.Assert.assertEquals;

public class SearchForm extends LoadableComponent<SearchForm> {
    private final WebDriver driver;

    public SearchForm(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void load() {
        driver.get("/search.html");
    }

    @Override
    protected void isLoaded() throws Error {
        assertEquals("Search", driver.getTitle());
    }

    public void searchFor(String query) {
        driver.findElement(By.cssSelector("input[name='q']")).sendKeys(query);
        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }
}
