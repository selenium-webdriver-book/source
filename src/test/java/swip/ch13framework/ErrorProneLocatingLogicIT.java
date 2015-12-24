package swip.ch13framework;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swip.framework.Config;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.By.linkText;

@RunWith(WebDriverRunner.class)
@Config(exclude = "browserName=htmlunit")
public class ErrorProneLocatingLogicIT {

    private final StopWatch stopWatch = new StopWatch();
    @Inject
    private WebDriver driver;

    @Before
    public void startStopWatch() {
        stopWatch.start();
    }

    @After
    public void print() {
        System.out.println("Time taken " + stopWatch);
    }

    @Test
    //  @Ignore("When the location is choose, the menu fades in over a few seconds. This test cannot deal with that.")
    public void errorProneLocatingLogic() {
        driver.get("/location-chooser.html");
        driver.findElement(linkText("change location")).click();
        WebElement tabMenu = driver.findElement(By.id("location"));
        tabMenu.findElement(linkText("CANADA")).click();
        tabMenu.findElement(linkText("Ontario")).click();
        assertEquals(0, tabMenu.findElements(linkText("Ontario")).size());
        assertEquals("Ontario", driver
            .findElement(By.cssSelector(".tools-location strong")) // <1>
            .getText());
    }
}
