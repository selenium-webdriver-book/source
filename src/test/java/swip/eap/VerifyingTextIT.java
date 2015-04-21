package swip.eap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.junit.SeleniumWebDriverRunner;
import swip.le.ElementBy;

import javax.inject.Inject;
import java.net.URI;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

@RunWith(SeleniumWebDriverRunner.class)
public class VerifyingTextIT {
    @Inject
    private WebDriver driver;
    @Inject
    private URI baseUrl;

    @Test
    public void easyToFindElement() throws Exception {
        driver.get(baseUrl + "/styled-elements.html");

        assertThat(driver.findElement(By.tagName("h1")).getText(),
                containsString("This page contains a variety of styled elements."));
    }

    @Test
    public void pageSourceMethod() throws Exception {
        driver.get(baseUrl + "/styled-elements.html");

        assertThat(driver.getPageSource(),
                containsString("This page contains a variety of styled elements."));
    }

    @Test
    public void whenPageSourceFails() throws Exception {
        driver.get(baseUrl + "/styled-elements.html");

        assertThat(driver.getPageSource(),
                containsString("<!-- a comment about the page -->")); // #A this element is not actually visible, yet this test passes, you might not want that
    }

    @Test
    public void xpathTextMethod() throws Exception {
        driver.get(baseUrl + "/styled-elements.html");

        assertNotNull(driver.findElement(ElementBy.partialText("visible paragraph")));
    }

    @Test
    public void streamMethod() throws Exception {
        driver.get(baseUrl + "/styled-elements.html");

        assertTrue(
                driver
                        .findElements(By.cssSelector("*")) // #B get every element on the page
                        .stream() // #C create a Java stream from the list
                        .anyMatch( // #D match any element that is both displayed and contains the expected text
                                e -> e.isDisplayed() &&
                                        e.getText().contains("This page contains a variety of styled elements.")
                        )
        );
    }
}
