package swip.ch11decorating.webdriverlistener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import swip.ch07managingwebdriver.Config;
import swip.ch07managingwebdriver.WebDriverRunner;

import javax.inject.Inject;
import java.io.File;

@RunWith(WebDriverRunner.class)
@Config(exclude = "browserName=htmlunit")
public class ScreenshotIT {
    @Inject
    private WebDriver driver;

    @Test
    public void listener() throws Exception {

        EventFiringWebDriver driver = new EventFiringWebDriver(this.driver); // <1>

        driver.register(new AbstractWebDriverEventListener() { // <2>

            @Override
            public void beforeNavigateTo(String url, WebDriver driver) {
                File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // <3>

                System.out.println("saved " + url + " as " + screenshotFile);
            }
        });

        driver.get("/index.html");
    }
}
