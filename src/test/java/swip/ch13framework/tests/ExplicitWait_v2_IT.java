package swip.ch13framework.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch13framework.v2.Browser;
import swip.ch13framework.v2.Element;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.openqa.selenium.By.linkText;

@RunWith(WebDriverRunner.class)
public class ExplicitWait_v2_IT {

    private Browser browser;

    @Inject
    private void setWebDriver(WebDriver driver) {
        this.browser = new Browser(driver);
    }

    @Test
    public void searchScope() throws Exception {
        browser.get("/location-chooser.html");
        browser.findElement(linkText("choose location")).click();
        Element tabMenu = browser.findElement(By.id("location"));
        tabMenu.untilFound(linkText("MEXICO")).click();
        tabMenu.untilFound(linkText("Cancun")).click();
        assertFalse(tabMenu.optionalElement(linkText("Toronto")).isPresent());
        assertEquals("Cancun", browser
                .findElement(By.cssSelector(".tools-location strong"))
                .getText());
    }
}
