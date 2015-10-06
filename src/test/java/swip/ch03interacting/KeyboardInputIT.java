package swip.ch03interacting;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import swip.framework.Config;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;
import java.net.URI;

import static org.junit.Assert.assertNotEquals;

@RunWith(WebDriverRunner.class)
// html unit does not implement RemoteWebDriver
@Config(exclude = {"browserName=safari", "browserName=iPhone", "browserName=htmlunit", "browserName=firefox"})
public class KeyboardInputIT<W extends WebDriver & HasInputDevices> {

    @Inject
    private W driver;
    @Inject
    private URI baseUrl;

    @Test
    public void completingAForm() throws Exception {
        driver.get(baseUrl + "/mailing-list.html");
        driver
                .findElement(By.name("email")) // #A locate the email input
                .sendKeys("john.doe@swip.com"); // #B you enter text here into the input

        driver.getKeyboard().sendKeys(Keys.TAB); // #C now you tab to the checkbox

        driver
                .switchTo().activeElement() // #D change to the currently active element, in this case the checkbox
                .sendKeys(" "); // #E press space to check the box

        driver.getKeyboard().pressKey(Keys.ENTER); // #F submit the form
        driver.getKeyboard().releaseKey(Keys.ENTER);
    }

    @Test
    @Ignore("this test does not work on Firefox")
    public void enteringBadEmailMeansEmailBorderChangesColor() throws Exception {

        driver.get("/login.html");
        WebElement email = driver.findElement(By.name("email"));
        String colorBefore =  email.getCssValue("border-color");
        email.sendKeys("invalid email");

        driver.findElement(By.name("password")).sendKeys("any password");

        assertNotEquals(colorBefore, email.getCssValue("border-color"));
    }
}
