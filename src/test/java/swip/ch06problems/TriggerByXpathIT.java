package swip.ch06problems;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.framework.WebDriverRunner;
import swip.tests.TestTimer;

import javax.inject.Inject;

@RunWith(WebDriverRunner.class)
public class TriggerByXpathIT extends TestTimer {

    @Inject
    private WebDriver browser;

    @Before
    public void setup() {
        browser.get("/react-datepicker.html");
    }

    @Test
    public void locateSuccessfully() {
        browser.findElement(By.xpath("//*[@id=\"app\"]/descendant::input"));
    }

}
