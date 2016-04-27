package swip.ch14elements.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import swip.framework.Browser;
import swip.framework.BrowserRunner;

import javax.inject.Inject;

import static swip.locators.Name.EMAIL;
import static swip.locators.Name.TERMS;
import static swip.locators.TagName.BUTTON;

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
