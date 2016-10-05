package swb.ch10javascript.usage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class JavascriptExecutorUsageIT {
    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/index.html");
    }

    @Test
    public void castingResult() throws Exception {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        long windowWidth = (long) jsExecutor
                .executeScript("return window.innerWidth");
    }

    @Test
    public void passingArgument() throws Exception {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(
                "var text = arguments[0];" +
                        "console.log(text)",
                "A string to log"
        );
    }

    @Test
    public void clickALink() throws Exception {

        WebElement link = driver.findElement(By.tagName("a"));

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("var link = arguments[0]; " +
                "link.click()", link);
    }

    @Test
    public void waitForAsyncJavaScript() throws Exception {

        driver.manage().timeouts()
                .setScriptTimeout(200, TimeUnit.MILLISECONDS);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        long startTime = System.currentTimeMillis();
        jsExecutor.executeAsyncScript(
                "var callback = arguments[arguments.length - 1];" +
                        "setTimeout(callback, 100);");

        assertThat(System.currentTimeMillis() - startTime, greaterThanOrEqualTo(100L));
    }

    @Test
    public void waitForAsyncJavaScriptWithReturn() throws Exception {

        driver.manage().timeouts()
                .setScriptTimeout(200, TimeUnit.MILLISECONDS);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String result = (String) jsExecutor.executeAsyncScript(
                "var callback = arguments[arguments.length - 1];" +
                        "setTimeout(function() {callback('Hello WebDriver!');}, 100);");

        assertEquals("Hello WebDriver!", result);
    }
}
