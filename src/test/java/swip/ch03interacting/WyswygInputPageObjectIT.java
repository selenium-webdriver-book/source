package swip.ch03interacting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swip.junit.Config;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;

@RunWith(SeleniumWebDriverRunner.class)
@Config(exclude = {"browserName=safari", "browserName=htmlunit"})
public class WyswygInputPageObjectIT {

    @Inject
    private WebDriver driver;

    @Test
    public void enterTextIntoAWyswygEditor() throws Exception {

        driver.get("/wyswyg-editor.html");
        WyswygInput wyswygInput = new WyswygInput(driver);

        wyswygInput.setText("A paragraph of text, some of which is");
        wyswygInput.appendItalicText(" italics.");
    }
}
