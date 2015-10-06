package swip.ch09javascript.time;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class PrintTheTimeIT {
    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/print-the-time.html");
    }

    @Test
    public void forceTheDateToASpecificValue() throws Exception {

        ((JavascriptExecutor) driver).executeScript(
                "var d = new Date(2016, 1, 1, 16, 23, 45);\n" +
                        "Date = function() {return d;}"
        );

        driver.findElement(By.linkText("Print The Time")).click();

        assertEquals("16:23:45", driver.findElement(By.id("time")).getText());
    }
}
