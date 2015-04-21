package swip.intro;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

@Ignore("will not run on CI")
public class HelloWebDriverIT { // we use the IT suffix for test in this book, this is the Maven convention for integration tests

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver(); // create a new driver which connected to an instance of the Firefox browser
    }

    @After
    public void tearDown() throws Exception {
        driver.quit(); // instruct the browser to quit
    }

    @Test
    public void helloWebDriver() throws Exception {

        driver.get("http://localhost:8080/hello-webdriver.html"); // open a web page in the browser

        WebElement pageHeading = driver.findElement(By.tagName("h1")); // locate an element on the current page, in this case the page's heading

        assertEquals("Hello WebDriver!", pageHeading.getText()); // verify that the heading is the value you expect
    }
}
