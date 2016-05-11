package swip.ch14elements.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import swip.framework.Browser;
import swip.framework.BrowserRunner;
import swip.framework.SearchScope;

import javax.inject.Inject;

import static swip.locators.Name.*;
import static swip.locators.TagName.BUTTON;

@RunWith(BrowserRunner.class)
public class RegistrationForm_v4_IT {

    @Inject
    private Browser driver;

    @Test
    public void register() throws Exception {
        driver.get("/registration-form-new.html");

        driver.setInputText(EMAIL, "john@doe.com");
        driver.setInputText(PASSWORD, "secret");
        driver.selectByVisibleText(HEAR_ABOUT, "Friend");       //<1>
        driver.setRadio(CONTACT, "email");
        driver.selectByVisibleText(INTEREST, "Movies", "Music");     //<2>
        driver.setInputText(TELLUS, "---");
        driver.setCheckboxValue(TERMS, true);
        driver.click(BUTTON);

        driver.await((SearchScope d) -> driver.getTitle().contains("Thank You"));
    }
}
