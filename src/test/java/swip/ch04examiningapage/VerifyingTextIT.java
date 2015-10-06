package swip.ch04examiningapage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch02locatingelements.ElementBy;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;
import java.net.URI;

import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(WebDriverRunner.class)
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
                anyOf(
                        containsString("<p id=\"invisible\" style=\"display:none;\">"),
                        containsString("<p style=\"display:none;\" id=\"invisible\">")
                )
        );
        assertThat(driver.getPageSource(),
                containsString("<!-- a comment about the page -->"));
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
