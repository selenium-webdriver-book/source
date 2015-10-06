package swip.ch06problems;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

@RunWith(WebDriverRunner.class)
public class ImplicitWaitIT {
    @Inject
    private WebDriver driver;

    @After
    public void tearDown() throws Exception {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

    }

    @Test
    public void printWait() throws Exception {

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

    }
}
