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
import static swip.locators.CssSelector.TOOLS_LOCATION_STRONG;
import static swip.locators.Id.LOCATION;
import static swip.locators.LinkText.CANCUN;
import static swip.locators.LinkText.CHOOSE_LOCATION;
import static swip.locators.LinkText.MEXICO;

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
        browser.untilFound(CHOOSE_LOCATION).click();                   //<1>
        Element tabMenu = new Element(browser.findElement(LOCATION));  //<2>
        tabMenu.untilFound(MEXICO).click();                            //<3>
        tabMenu.untilFound(CANCUN).click();
        assertEquals("Cancun", browser.findElement(TOOLS_LOCATION_STRONG)
            .getText());
    }
}
