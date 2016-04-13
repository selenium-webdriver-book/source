package swip.ch12wrapping.v1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.FindsByTagName;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class FindElementExampleIT {

    @Inject
    private WebDriver driver;
    private DelegatingWebDriver delegatingWebDriver;

    @Before
    public void setUp() throws Exception {
        driver.get("/index.html");
        delegatingWebDriver = new DelegatingWebDriver(driver);
    }

    @Test()
    public void findHeading1Element() throws Exception {
        WebElement h1 = ((FindsByTagName) driver).findElementByTagName("h1");
        assertEquals("Selenium WebDriver In Practice - Index", h1.getText());
    }

    @Test(expected = ClassCastException.class)
    public void findHeading1ElementButGotException() throws Exception {
        ((FindsByTagName) delegatingWebDriver).findElementByTagName("h1");
    }

}
