package swip.iwe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.HasInputDevices;
import swip.junit.Config;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;

@RunWith(SeleniumWebDriverRunner.class)
// html unit does not implement RemoteWebDriver
@Config(exclude = {"browserName=safari", "browserName=iPhone", "browserName=htmlunit"})
public class KeyboardInputIT<W extends WebDriver & HasInputDevices> {

    // TODO - cover RemoteWebDriver vs WebDriver
    @Inject
    private W driver;

    @Test
    public void completingAForm() throws Exception {
        driver.get("http://localhost:8080/mailing-list.html");
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
}
