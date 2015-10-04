package swip.ch09javascript.usage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swip.ch07managingwebdriver.Config;
import swip.ch07managingwebdriver.WebDriverRunner;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

@RunWith(WebDriverRunner.class)
// htmlunit supports JavaScript, just not these examples.
@Config(exclude = "browserName=htmlunit")
public class JavascriptExectorUsageIT {
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

    @Test
    public void clickALink() throws Exception {

        WebElement link = driver.findElement(By.tagName("a"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", link);
    }

    @Test
    public void waitOneHundredMilliseconds() throws Exception {

        driver.manage().timeouts()
                .setScriptTimeout(200, TimeUnit.MILLISECONDS);

        ((JavascriptExecutor) driver).executeAsyncScript(
                "setTimeout(arguments[arguments.length - 1], 100)");
    }
}
