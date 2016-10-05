package swb.ch02locatingelements;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;

@RunWith(WebDriverRunner.class)
public class AdvancedXpathExamplesIT {

    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/styled-elements.html");
    }

    @Test(expected = NoSuchElementException.class)
    public void containsFail() throws Exception {
        driver.findElement(By.xpath("//*[contains(text(),'A paragraph XXX with this text in bold')]"));
    }

    @Test
    public void contains() throws Exception {
        driver.findElement(By.xpath("//*[contains(normalize-space(.),'A paragraph with this text in bold')]"));
    }

    @Test
    public void within() throws Exception {
        driver.findElement(By.xpath("//div[contains(.,'A visible paragraph')]/form"));
    }
}
