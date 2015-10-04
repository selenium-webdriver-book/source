package swip.ch12framework.v4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch07managingwebdriver.Config;
import swip.ch07managingwebdriver.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.openqa.selenium.By.linkText;

@RunWith(WebDriverRunner.class)
@Config(exclude = "browserName=htmlunit")
public class ExplicitWaitIT {

    private Browser browser;

    @Inject
    private void setWebDriver(WebDriver driver) {
        this.browser = new Browser(driver);
    }

    @Test
    public void searchScope() throws Exception {
        browser.get("/location-chooser.html");
        browser.findElement(linkText("change location")).click();
        Element tabMenu = browser.findElement(By.id("location"));
        tabMenu.untilFound(linkText("CANADA")).click();
        tabMenu.untilFound(linkText("Ontario")).click();
        assertFalse(tabMenu.optionalElement(linkText("Ontario")).isPresent());
        assertEquals("Ontario", browser
                .findElement(By.cssSelector(".tools-location strong"))
                .getText());
    }
}
