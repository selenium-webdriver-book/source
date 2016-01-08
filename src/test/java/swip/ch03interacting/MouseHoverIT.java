package swip.ch03interacting;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;
import java.net.URI;

@RunWith(WebDriverRunner.class)
public class MouseHoverIT {

    @Inject
    private WebDriver driver;
    @Inject
    private URI baseUrl;

    @Test
    public void hoverOver() throws Exception {
        driver.get(baseUrl + "/mouse-hover.html");

        new Actions(driver)
                .moveToElement(driver.findElement(By.id("target")))
                .build()
                .perform();
    }
}