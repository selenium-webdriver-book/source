package swip.ch12framework;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.By.linkText;

public class ErrorProneLocatingLogicIT {

    private static final String CHOOSE_LOCATION_PAGE = "http://localhost:8080/location-chooser.html";// http://www.ticketfly.com";
    // TODO - xpath vs css selector
    private static final By CHOSEN_LOCATION = By.cssSelector(".tools-location strong"); //By.xpath("div[@class='tools-location']/descendant::strong");

    static {
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver");
    }

    private final WebDriver webDriver = new ChromeDriver();

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }

    @Test
    @Ignore("When the location is choose, the menu fades in over a few seconds. This test cannot deal with that.")
    public void errorProneLocatingLogic() {
        webDriver.get(CHOOSE_LOCATION_PAGE);
        webDriver.findElement(linkText("change location")).click();
        WebElement location = webDriver.findElement(By.id("location"));
        location.findElement(linkText("CANADA")).click();
        WebElement element = location.findElement(linkText("Ontario"));
        element.click();
        assertEquals(0, location.findElements(linkText("Ontario")).size());
        assertEquals("Ontario", webDriver
                .findElement(
                        CHOSEN_LOCATION
                )
                .getText());
    }

    @Test
    public void usingImplicitWait() {
        webDriver.manage().timeouts().implicitlyWait(30, SECONDS);
        webDriver.get(CHOOSE_LOCATION_PAGE);
        webDriver.findElement(linkText("change location")).click();
        WebElement tabMenu = webDriver.findElement(By.id("location"));
        tabMenu.findElement(linkText("CANADA")).click();
        WebElement element = tabMenu.findElement(linkText("Ontario"));
        element.click();
        // TODO - the next line does not work?
        // assertEquals(0, tabMenu.findElements(linkText("Ontario")).size());
        assertEquals("Ontario", webDriver
                .findElement(CHOSEN_LOCATION)
                .getText());
    }

    @Test
    public void usingExplicitWait() {
        webDriver.get(CHOOSE_LOCATION_PAGE);
        webDriver.findElement(linkText("change location")).click();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 5);
        WebElement location = webDriverWait.until((WebDriver d) -> webDriver.findElement(By.id("location")));
        FluentWait<WebElement> webElementWait
                = new FluentWait<>(location)
                .withTimeout(30, SECONDS)
                .pollingEvery(5, MILLISECONDS)
                .ignoring(Exception.class);
        WebElement canada = webElementWait.until(
                (WebElement element) -> location.findElement(linkText("CANADA")));
        canada.click();
        WebElement allCanada = webElementWait.until(
                (WebElement element) -> location.findElement(linkText("Ontario"))
        );
        allCanada.click();
        // TODO - this next line does not seem to work?
        // assertEquals(0, webDriver.findElements(linkText("Ontario")).size());
        assertEquals("Ontario", webDriver
                .findElement(CHOSEN_LOCATION)
                .getText());
    }
}
