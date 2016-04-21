package swip.ch13framework.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch13framework.v1.Browser;
import swip.ch13framework.v1.Element;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.By.linkText;

@RunWith(WebDriverRunner.class)
public class ExplicitWait_v1_IT {

    private Browser browser;

    @Inject
    private void setWebDriver(WebDriver driver) {
        this.browser = new Browser(driver);
    }

    @Test
    public void explicitWait() throws Exception {
        browser.get("/location-chooser.html");
        browser.untilFound(linkText("choose location")).click();
        Element tabMenu = new Element(browser.findElement(By.id("location")));
        tabMenu.untilFound(linkText("MEXICO")).click();
        tabMenu.untilFound(linkText("Cancun")).click();
        assertEquals("Cancun", browser
            .findElement(By.cssSelector(".tools-location strong"))
            .getText());
    }
}
