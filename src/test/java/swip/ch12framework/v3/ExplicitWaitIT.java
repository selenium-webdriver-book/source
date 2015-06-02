package swip.ch12framework.v3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch07managingwebdriver.Config;
import swip.ch07managingwebdriver.SeleniumWebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.By.linkText;

@RunWith(SeleniumWebDriverRunner.class)
@Config(exclude = "browserName=htmlunit")
public class ExplicitWaitIT {

    private Browser browser;

    @Inject
    private void setWebDriver(WebDriver driver) {
        this.browser = new Browser(driver);
    }

    @Test
    public void explicitWait() throws Exception {
        browser.get("/location-chooser.html");
        browser.findElement(linkText("change location")).click();
        Element tabMenu = browser.findElement(By.id("location"));
        tabMenu.untilFound(linkText("CANADA")).click();
        tabMenu.untilFound(linkText("Ontario")).click();
        assertEquals("Ontario", browser
                .findElement(By.cssSelector(".tools-location strong"))
                .getText());
    }
}
