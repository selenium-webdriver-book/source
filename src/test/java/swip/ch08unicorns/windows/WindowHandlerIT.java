package swip.ch08unicorns.windows;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch07managingwebdriver.Config;
import swip.ch07managingwebdriver.SeleniumWebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(SeleniumWebDriverRunner.class)
@Config(exclude = {"browserName=safari"})
public class WindowHandlerIT {
    @Inject
    private WebDriver driver;

    @Test
    public void openNewWindow() throws Exception {
        driver.get("http://localhost:8080/open-a-new-window.html");

        new WindowHandler(driver) {
            @Override
            public void openWindow(WebDriver driver) {
                driver.findElement(By.tagName("a")).click();
            }

            @Override
            public void useWindow(WebDriver driver) {
                assertEquals("You Are In The New Window", driver.findElement(By.tagName("h1")).getText());
            }
        }.run();
    }
}
