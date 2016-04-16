package swip.ch17reactdatepicker.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import swip.framework.Browser;
import swip.framework.BrowserRunner;
import swip.tests.TestTimer;

import javax.inject.Inject;

import static swip.locators.ReactByXpath.TRIGGER_BY;

@RunWith(BrowserRunner.class)
public class TriggerByXpathIT extends TestTimer {

    @Inject
    private Browser browser;

    @Before
    public void setup() {
        browser.get("/react-datepicker.html");
    }

    @Test
    public void locateSuccessfully() {
        browser.findElement(By.xpath("//*[@id=\"app\"]/descendant::input"));
    }

    @Test
    public void trigger() {
         browser.click(TRIGGER_BY);
    }
}
