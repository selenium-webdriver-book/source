package swip.ch09javascript.safari;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import static org.junit.Assert.assertEquals;

public class SafariPasswordIT {
    private WebDriver driver = new SafariDriver();

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void passwordPopup() throws Exception {
        driver.get("http://localhost:8080/login.html");

        driver.findElement(By.name("email")).sendKeys("joe@email.com");
        driver.findElement(By.name("password")).sendKeys("secret");
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        String headingText = driver.findElement(By.tagName("h1")).getText();

        assertEquals("You Are Logged In", headingText);
    }

}
