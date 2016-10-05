package swb.ch02locatingelements;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;

@RunWith(WebDriverRunner.class)
public class LocatorExamplesIT {

    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/login.html");
    }

    @Test
    public void byLinkText() throws Exception {
        driver.findElement(By.linkText("Forgotten Password"));
    }

    @Test
    public void byPartialLinkText() throws Exception {
        driver.findElement(By.partialLinkText("Forgotten Password"));
        driver.findElement(By.partialLinkText("Forgotten "));
        driver.findElement(By.partialLinkText("en Passwo"));
    }

    @Test
    public void byId() throws Exception {
        driver.findElement(By.id("change-password"));
    }

    @Test
    public void byClassName() throws Exception {
        driver.findElement(By.className("btn"));
    }

    @Test
    public void byName() throws Exception {
        driver.findElement(By.name("email"));
    }

    @Test
    public void byTagName() throws Exception {
        driver.findElement(By.tagName("a"));
    }
}
