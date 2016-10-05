package swb.misc;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import static org.junit.Assert.assertEquals;

@Ignore("requires safari driver installed")
public class SafariPasswordIT {
    private WebDriver driver = new SafariDriver();

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Ignore("this test will not complete as it will wait for the password prompt")
    @Test
    public void passwordPopupThatCannotComplete() throws Exception {
        driver.get("/login.html");

        driver.findElement(By.name("email")).sendKeys("joe@email.com");
        driver.findElement(By.name("password")).sendKeys("secret");
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        String headingText = driver.findElement(By.tagName("h1")).getText();

        assertEquals("You Are Logged In", headingText);
    }

    @Test
    public void passwordPopup() throws Exception {
        driver.get("/login.html");

        driver.findElement(By.name("email")).sendKeys("joe@email.com");
        driver.findElement(By.name("password")).sendKeys("secret");

        // recent Safari will try and save a regardless of visibility
        ((JavascriptExecutor) driver).executeScript(
                "Array.prototype.slice.call(document.getElementsByTagName('input'))" +
                        ".forEach(function(e){e.name=e.type=='password1'?'foo':e.name;});");

        ((JavascriptExecutor) driver).executeScript(
                "Array.prototype.slice.call(document.getElementsByTagName('input'))" +
                        ".forEach(function(e){e.type=e.type=='password'?'text':e.type;});");

        driver.findElement(By.cssSelector("input[type='submit']")).click();

        String headingText = driver.findElement(By.tagName("h1")).getText();

        assertEquals("You Are Logged In", headingText);
    }

}
