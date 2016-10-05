package swb.ch02locatingelements;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;

@RunWith(WebDriverRunner.class)
public class CssEquivilantsIT {

    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/login.html");
    }

    @Test
    public void byDi() throws Exception {
        driver.findElement(By.cssSelector("#change-password"));
    }

    @Test
    public void byClassName() throws Exception {
        driver.findElement(By.cssSelector(".btn"));
    }

    @Test
    public void byName() throws Exception {
        driver.findElement(By.cssSelector("*[name='email']"));
    }

    @Test
    public void byTagName() throws Exception {
        driver.findElement(By.cssSelector("a"));
    }
}
