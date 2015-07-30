package swip.ch05pageobjects.e;

import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class SearchPage {
    private final WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void assertPageTitleIs(String expectedTitle) {
        assertEquals(expectedTitle, driver.getTitle());
    }

    public void verifyPageTitleIs(String expectedTitle) {
        String actualTitle = driver.getTitle();
        if (!expectedTitle.equals(actualTitle)) {
            throw new IllegalStateException("expected " + expectedTitle + " but got " + actualTitle);
        }
    }
}
