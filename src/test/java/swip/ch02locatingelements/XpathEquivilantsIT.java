package swip.ch02locatingelements;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.framework.Config;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

@RunWith(WebDriverRunner.class)
@Config(exclude = "browserName=htmlunit")
public class XpathEquivilantsIT {

    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/login.html");
    }

    @Test
    public void byId() throws Exception {
        driver.findElement(By.xpath("//*[@id='change-password']"));
    }

    @Test
    public void byClassName() throws Exception {
        driver.findElement(By.xpath("//*[contains(concat(' ',normalize-space(@class),' '),' btn ')]"));
    }

    @Test
    public void byName() throws Exception {
        driver.findElement(By.xpath("//*[@name='email']"));
    }

    @Test
    public void byTagName() throws Exception {
        driver.findElement(By.xpath("//a"));
    }
}
