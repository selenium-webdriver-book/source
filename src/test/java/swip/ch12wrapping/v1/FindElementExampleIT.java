package swip.ch12wrapping.v1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.FindsByTagName;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

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

    @Test(expected = ClassCastException.class)
    public void findHeading1ElementButGotException() throws Exception {
        ((FindsByTagName) delegatingWebDriver).findElementByTagName("h1");
    }

}
