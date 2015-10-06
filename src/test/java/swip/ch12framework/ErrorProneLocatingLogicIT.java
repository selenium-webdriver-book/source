package swip.ch12framework;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import swip.ch07managingwebdriver.injecting.Config;
import swip.ch07managingwebdriver.injecting.WebDriverRunner;

import javax.inject.Inject;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.By.linkText;

@RunWith(WebDriverRunner.class)
@Config(exclude = "browserName=htmlunit")
public class ErrorProneLocatingLogicIT {

    @Inject
    private WebDriver driver;

    @Test
    @Ignore("When the location is choose, the menu fades in over a few seconds. This test cannot deal with that.")
    public void errorProneLocatingLogic() {
        driver.get("http://localhost:8080/location-chooser.html");
        driver.findElement(linkText("change location")).click();
        WebElement tabMenu = driver.findElement(By.id("location"));
        tabMenu.findElement(linkText("CANADA")).click();
        tabMenu.findElement(linkText("Ontario")).click();
        assertEquals(0, tabMenu.findElements(linkText("Ontario")).size());
        assertEquals("Ontario", driver
                .findElement(By.cssSelector(".tools-location strong")) // <1>
                .getText());
    }

    @Test
    public void usingImplicitWait() {
        driver.manage().timeouts().implicitlyWait(5, SECONDS); // <1>
        driver.get("http://localhost:8080/location-chooser.html");
        driver.findElement(linkText("change location")).click();
        WebElement tabMenu = driver.findElement(By.id("location"));
        tabMenu.findElement(linkText("CANADA")).click();
        tabMenu.findElement(linkText("Ontario")).click();
        assertEquals(0, tabMenu.findElements(linkText("Ontario")).size());
        assertEquals("Ontario", driver
                .findElement(By.cssSelector(".tools-location strong"))
                .getText());
    }

    @Test
    public void usingExplicitWait() {
        driver.get("http://localhost:8080/location-chooser.html");
        driver.findElement(linkText("change location")).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5); // <1>

        WebElement tabMenu = webDriverWait
                .until((WebDriver d) -> driver.findElement(By.id("location")));

        FluentWait<WebElement> webElementWait = new FluentWait<>(tabMenu) // <2>
                .withTimeout(5, SECONDS)
                .pollingEvery(100, MILLISECONDS)
                .ignoring(Exception.class);

        webElementWait.until(
                (WebElement element) -> tabMenu.findElement(linkText("CANADA")))
                .click();
        webElementWait
                .until((WebElement element) -> tabMenu.findElement(linkText("Ontario")))
                .click();
        assertEquals(0, tabMenu.findElements(linkText("Ontario")).size());
        assertEquals("Ontario", driver
                .findElement(By.cssSelector(".tools-location strong"))
                .getText());
    }
}
