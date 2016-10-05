package swb.ch14elements.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import swb.framework.Browser;
import swb.framework.BrowserRunner;

import javax.inject.Inject;

import static swb.locators.Name.EMAIL;
import static swb.locators.Name.TERMS;
import static swb.locators.TagName.BUTTON;

@RunWith(BrowserRunner.class)
public class MouseInputIT {

    @Inject
    private Browser driver;

    @Test
    public void completeAFormUsingBothMouseAndKeyboard() throws Exception {
        driver.get("/mailing-list.html");
        driver.setInputText(EMAIL, "john.doe@swip.com");
        driver.setCheckboxValue(TERMS, true);
        driver.doubleClick(BUTTON);
    }
}
