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
public class FindByXpathIT extends TestTimer {

    @Inject
    private WebDriver driver;

    @Before
    public void setup() {
        driver.get("/react-datepicker.html");
    }

    @Test
    public void locateSuccessfully() {
        driver.findElement(
            By.xpath("//*[@id=\"app\"]/descendant::input"));
    }

}
