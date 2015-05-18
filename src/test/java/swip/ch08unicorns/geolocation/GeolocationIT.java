package swip.ch08unicorns.geolocation;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class GeolocationIT {

    private final WebDriver driver = GeolocationConfigurableWebDriver
            .create(FirefoxDriver::new, GeolocationStatus.OK, 51.5106766, -0.1231314);

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void ok() throws Exception {

        driver.get("http://localhost:8080/geolocation.html");

        WebElement statusElement = driver.findElement(By.id("status"));

        new WebDriverWait(driver, 10).until((WebDriver d) -> !statusElement.getText().equals("Loading...")); // You usually need to wait for location to be established.

        assertEquals("You state you are at + 51.5106766, -0.1231314", statusElement.getText());
    }
}
