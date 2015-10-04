package swip.ch03interacting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import swip.ch02locatingelements.InputBy;
import swip.ch07managingwebdriver.WebDriverRunner;

import javax.inject.Inject;
import java.net.URI;

@RunWith(WebDriverRunner.class)
public class FormIT {
    @Inject
    private WebDriver driver;
    @Inject
    private URI baseUrl;

    @Test
    public void completingAForm() throws Exception {
        driver.get(baseUrl + "/registration-form.html");

        ((JavascriptExecutor) driver).executeScript("Array.prototype.slice.call(" +
                "document.getElementsByTagName('input')).forEach(function(e){" +
                "   e.type=e.type=='password'?'text':e.type;" +
                "});");

        driver.findElement(By.name("email")).sendKeys("john@doe.com");
        driver.findElement(By.name("password")).sendKeys("secret");

        new Select(driver.findElement(By.name("hearAbout"))).selectByVisibleText("Friend");

        driver.findElement(InputBy.label("email")).click();

        Select interestsSelect = new Select(driver.findElement(By.name("interest")));
        interestsSelect.selectByVisibleText("Movies");
        interestsSelect.selectByVisibleText("Music");

        driver.findElement(By.tagName("button")).click();

        new WebDriverWait(driver, 1000).until((WebDriver driver) -> driver.getTitle().contains("Thank You"));
    }
}
