package swip.ch06pageobjects.a;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchForm {
    private final WebDriver driver;

    public SearchForm(WebDriver driver) {
        this.driver = driver;
    }

    public void setQuery(String query) {
        driver.findElement(By.cssSelector("input[name='q']"))
                .sendKeys(query);
    }

    public void submit() {
        driver.findElement(By.cssSelector("input[type='submit']"))
                .click();
    }
}
