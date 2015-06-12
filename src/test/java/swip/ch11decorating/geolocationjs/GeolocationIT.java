package swip.ch11decorating.geolocationjs;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import swip.ch07managingwebdriver.Config;
import swip.ch07managingwebdriver.SeleniumWebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(SeleniumWebDriverRunner.class)
@Config(exclude = "browserName=htmlunit")
public class GeolocationIT<W extends WebDriver & JavascriptExecutor> {
    @Inject
    private W driver;

    @Before
    public void setUp() throws Exception {
        driver.get("http://localhost:8080/geolocation.html");
    }

    @Test
    public void geolocationInjection() throws Exception {

        driver.executeScript(
                String.format(
                        "navigator.geolocation = navigator.geolocation || {};" +
                                "navigator.geolocation.getCurrentPosition = function(ok,err){" +
                                "ok({'coords': {'latitude': %s, 'longitude': %s}});" +
                                "}",
                        51.5106766,
                        -0.1231314
                ));

        driver.findElement(By.id("locate")).click();

        WebElement statusElement = driver.findElement(By.id("status"));

        new WebDriverWait(driver, 10).until((WebDriver d) -> !statusElement.getText().equals("Loading..."));

        assertEquals("You state you are at + 51.5106766, -0.1231314", statusElement.getText());
    }

    @Test
    public void positionError() throws Exception {

        driver.executeScript(
                String.format(
                        "navigator.geolocation = navigator.geolocation || {};" +
                        "navigator.geolocation.getCurrentPosition = function(ok,err){err({'error': {'PERMISSION_DENIED': 1, 'POSITION_UNAVAILABLE': 2, 'TIMEOUT': 3}, 'code': %d, 'message': '%s'});}",
                        1,
                        "User denied Geolocation"
                ));

        driver.findElement(By.id("locate")).click();

        WebElement statusElement = driver.findElement(By.id("status"));

        new WebDriverWait(driver, 10).until((WebDriver d) -> !statusElement.getText().equals("Loading..."));

        assertEquals("Error: 1 User denied Geolocation", statusElement.getText());
    }
}
