package swip.iwe;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import swip.junit.Config;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;

@RunWith(SeleniumWebDriverRunner.class)
@Config(exclude = {"browserName=safari"})
public class MouseHoverIT {

    @Inject
    private WebDriver driver;

    @Test
    public void hoverOver() throws Exception {
        driver.get("http://localhost:8080/mouse-hover.html");

        new Actions(driver)
                .moveToElement(driver.findElement(By.id("target")))
                .build()
                .perform();
    }
}