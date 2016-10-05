package swb.ch12wrapping.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;
import java.io.File;

@RunWith(WebDriverRunner.class)
public class ScreenshotIT {
    @Inject
    private WebDriver driver;

    @Test
    public void listener() throws Exception {

        EventFiringWebDriver driver = new EventFiringWebDriver(this.driver);

        driver.register(new AbstractWebDriverEventListener() {

            @Override
            public void afterNavigateTo(String url, WebDriver driver) {
                File screenshotFile = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

                System.out.println("saved " + url + " as " + screenshotFile);
            }
        });

        driver.get("/index.html");
    }
}
