package swip;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class FailingIT {
    @Inject
    private WebDriver webDriver;

    @Test
    public void test() throws Exception {
        webDriver.get("/");

        assertEquals("", webDriver.findElement(By.tagName("h1")).getText());
    }
}
