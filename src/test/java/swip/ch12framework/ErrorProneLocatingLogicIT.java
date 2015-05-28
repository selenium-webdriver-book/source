package swip.ch12framework;

import com.google.common.base.Function;
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

    @Test
    public void errorProneLocatingLogic() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(linkText("change location")).click();
        WebElement location = webDriver.findElement(By.id("location"));
        location.findElement(linkText("CANADA")).click();
        WebElement element = location.findElement(linkText("Ontario"));
        element.click();
        assertEquals(0, location.findElements(linkText("Ontario")).size());
        assertEquals("Ontario", webDriver
                .findElement(
                        By.xpath("div[@class='tools-location']/descendant::strong")
                )
                .getText());
    }

    @Test
    public void usingImplicitWait() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(30, SECONDS);
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(linkText("change location")).click();
        WebElement tabMenu = webDriver.findElement(By.id("location"));
        tabMenu.findElement(linkText("CANADA")).click();
        WebElement element = tabMenu.findElement(linkText("Ontario"));
        element.click();
        assertEquals(0, tabMenu.findElements(linkText("Ontario")).size());
        assertEquals("Ontario", webDriver
                .findElement(By.xpath("div[@class='tools-location']/descendant::strong"))
                .getText());
    }

    @Test
    public void usingExplicitWait() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.ticketfly.com");
        webDriver.findElement(linkText("change location")).click();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 5);
        WebElement location = webDriverWait.until(
                new Function<WebDriver, WebElement>() {

                    @Override
                    public WebElement apply(WebDriver webDriver) {
                        return webDriver.findElement(By.id("location"));
                    }
                });
        FluentWait<WebElement> webElementWait
                = new FluentWait<WebElement>(location)
                .withTimeout(30, SECONDS)
                .pollingEvery(5, MILLISECONDS)
                .ignoring(Exception.class);
        WebElement canada = webElementWait.until(
                new Function<WebElement, WebElement>() {

                    @Override
                    public WebElement apply(WebElement element) {
                        return location.findElement(linkText("CANADA"));
                    }
                });
        canada.click();
        WebElement allCanada = webElementWait.until(
                new Function<WebElement, WebElement>() {
                    @Override
                    public WebElement apply(WebElement element) {
                        return location.findElement(linkText("Ontario"));
                    }
                }
        );
        allCanada.click();
        assertEquals(0, webDriver.findElements(linkText("Ontario")).size());
        assertEquals("Ontario", webDriver
                .findElement(By.xpath("//div[@class='tools']/descendant::strong"))
                .getText());
    }
}
