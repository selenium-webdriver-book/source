package swip.ch02locatingelements;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch07managingwebdriver.SeleniumWebDriverRunner;

import javax.inject.Inject;

@RunWith(SeleniumWebDriverRunner.class)
public class AdvancedCssExamplesIT {

    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/login.html");
    }

    @Test
    public void singleAttribute() throws Exception {
        driver.findElement(By.cssSelector("input[name='email']"));
    }

    @Test
    public void multipleAttributes() throws Exception {
        driver.findElement(By.cssSelector("input[type='text'][name='email']"));
    }

    @Test
    public void attributePrefix() throws Exception {
        driver.findElement(By.cssSelector("input[name^='pass']"));
    }

    @Test
    public void attributeSuffix() throws Exception {
        driver.findElement(By.cssSelector("input[name$='word']"));
    }

    @Test
    public void attributeInfix() throws Exception {
        driver.findElement(By.cssSelector("input[name*='sswo']"));
    }

    @Test
    public void sibling() throws Exception {
        driver.findElement(By.cssSelector("input[name='password'] + input[type='submit']"));
    }

    @Test
    public void looseSibling() throws Exception {
        driver.findElement(By.cssSelector("input[name='email'] ~ input[type='submit']"));
    }

    @Test
    public void directDescendant() throws Exception {
        driver.findElement(By.cssSelector("div > input[name='email']"));
    }

    @Test
    public void anyDescendant() throws Exception {
        driver.findElement(By.cssSelector("form input[name='email']"));
    }
}
