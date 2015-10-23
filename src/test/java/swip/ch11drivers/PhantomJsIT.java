package swip.ch10drivers;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import static org.junit.Assert.assertEquals;

@Ignore("only runs with PhantomJS")
public class PhantomJsIT {
    private final WebDriver driver = new PhantomJSDriver();

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void helloPhantomJs() throws Exception {
        driver.get("http://localhost:8080/hello-webdriver.html");

        assertEquals("Hello WebDriver!", driver.findElement(By.tagName("h1")).getText());
    }
}
