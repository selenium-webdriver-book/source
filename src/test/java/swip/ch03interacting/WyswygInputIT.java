package swip.ch03interacting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;
import java.net.URI;

@RunWith(WebDriverRunner.class)
public class WyswygInputIT {

    @Inject
    private WebDriver driver;
    @Inject
    private URI baseUrl;

    @Test
    public void enterTextIntoAWyswgyEditor() throws Exception {
        driver.get(baseUrl + "/wyswyg-editor.html");

        WebElement editorFrame = driver.findElement(By.id("editor_ifr")); // #1 find the correct iframe

        driver.switchTo().frame(editorFrame);

        WebElement body = driver.findElement(By.tagName("body")); // #2 get the body element

        body.clear(); // #3 clear the existing text
        body.sendKeys("A paragraph of text, some of which is"); // #4 send our un-formatted text

        driver.switchTo().defaultContent(); // #5 switch back to the original frame
        driver.findElement(By.className("mce-i-italic")).click(); // #6 click the bold button

        driver.switchTo().frame(editorFrame); // #7 switch back to the editor
        body.sendKeys(" italics."); // #8 type italic text

        driver.switchTo().defaultContent();
        driver.findElement(By.className("mce-i-italic")).click();  // #9 deselect italics
    }
}
