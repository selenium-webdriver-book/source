package swb.ch13framework.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swb.ch13framework.v2.Browser;
import swb.ch13framework.v2.Element;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static swb.locators.CssSelector.TOOLS_LOCATION_STRONG;
import static swb.locators.Id.LOCATION;
import static swb.locators.LinkText.*;

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
        Element tabMenu = browser.await(LOCATION);
        tabMenu.click(MEXICO);
        tabMenu.click(CANCUN);
        assertFalse(tabMenu.isPresent(CANCUN));       //<1>
        assertEquals("Cancun", browser.getText(TOOLS_LOCATION_STRONG));
    }
}
