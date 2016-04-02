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
                .perform();
    }
}