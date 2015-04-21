package swip.le;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SeleniumWebDriverRunner.class)
public class FindElementExampleIT {

    @Inject
    private WebDriver driver;

    @Test
    public void findHeading1Element() throws Exception {

        WebElement heading1Element = driver.findElement(By.tagName("h1"));
    }

    @Test
    public void findParagraphElements() throws Exception {

        List<WebElement> paragraphElement = driver.findElements(By.tagName("p"));
    }
}
