package swip.ch09unicorns.actionchains;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class MouseHoverIT {
    @Inject
    private WebDriver driver;

    @Test
    public void mouseMove() throws Exception {
        driver.get("/mouse-hover.html");

        new Actions(driver)
                .moveToElement(driver.findElement(By.id("target")))
                .perform();

        assertEquals("Mouse Over",
                driver.findElement(By.id("target")).getText());
    }
}
