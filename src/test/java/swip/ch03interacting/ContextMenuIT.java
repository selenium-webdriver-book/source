package swip.ch03interacting;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import swip.junit.Config;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;
import java.net.URI;

@RunWith(SeleniumWebDriverRunner.class)
@Config(exclude = {"browserName=safari", "browserName=htmlunit"})
public class ContextMenuIT {

    @Inject
    private WebDriver driver;
    @Inject
    private URI baseUrl;

    @Test
    public void showContextMenu() throws Exception {
        driver.get(baseUrl + "/context-menu.html");

        new Actions(driver)
                .contextClick(driver.findElement(By.id("hascontextmenu")))
                .build()
                .perform();
    }
}