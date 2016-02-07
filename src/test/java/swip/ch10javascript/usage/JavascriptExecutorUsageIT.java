package swip.ch10javascript.usage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

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
                "console.log(arguments[0])",
                "A string to log"
        );
    }

    @Test
    public void clickALink() throws Exception {

        WebElement link = driver.findElement(By.tagName("a"));

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click()", link);
    }

    @Test
    public void waitOneHundredMilliseconds() throws Exception {

        driver.manage().timeouts()
                .setScriptTimeout(200, TimeUnit.MILLISECONDS);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeAsyncScript(
                "setTimeout(arguments[arguments.length - 1], 100)");
    }
}
