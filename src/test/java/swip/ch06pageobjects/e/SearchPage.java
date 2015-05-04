package swip.ch06pageobjects.e;

import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class SearchPage {
    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void assertPageTitleIs(String expectedTitle) {
        assertEquals(expectedTitle, driver.getTitle());
    }
}
