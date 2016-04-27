package swip.ch13framework.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swip.ch13framework.v2.Browser;
import swip.ch13framework.v2.Element;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static swip.locators.CssSelector.TOOLS_LOCATION_STRONG;
import static swip.locators.Id.LOCATION;
import static swip.locators.LinkText.*;

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
        browser.click(CHOOSE_LOCATION);
        Element tabMenu = browser.untilFound(LOCATION);
        tabMenu.click(MEXICO);
        tabMenu.click(CANCUN);
        assertFalse(tabMenu.isPresent(CANCUN));       //<1>
        assertEquals("Cancun", browser.getText(TOOLS_LOCATION_STRONG));
    }
}
