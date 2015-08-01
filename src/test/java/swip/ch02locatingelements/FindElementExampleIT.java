package swip.ch02locatingelements;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swip.ch07managingwebdriver.SeleniumWebDriverRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SeleniumWebDriverRunner.class)
public class FindElementExampleIT {

    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/index.html");
    }

    @Test
    public void findHeading1Element() throws Exception {

        WebElement heading1Element = driver.findElement(By.tagName("h1"));
    }

    @Test
    public void findParagraphElements() throws Exception {

        List<WebElement> paragraphElement = driver.findElements(By.tagName("p"));
    }
}
