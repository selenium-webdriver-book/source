package swip.ch09javascript;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import swip.ch07managingwebdriver.Config;
import swip.ch07managingwebdriver.SeleniumWebDriverRunner;

import javax.inject.Inject;

@RunWith(SeleniumWebDriverRunner.class)
// htmlunit acutally suppport JavaScript, just not these examples.
@Config(exclude = "browserName=html")
public class UsageIT {
    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/index.html");
    }

    @Test
    public void castingResult() throws Exception {
        long windowWidth = (long) ((JavascriptExecutor) driver) // <1>
                .executeScript("return window.innerWidth"); // <2>
    }

    @Test
    public void passingArgument() throws Exception {
        ((JavascriptExecutor) driver).executeScript(
                "console.log(arguments[0])", // <1>
                "A string to log" // <2>
        );
    }
}
