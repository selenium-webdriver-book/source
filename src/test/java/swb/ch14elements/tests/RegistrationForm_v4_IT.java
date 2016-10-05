package swb.ch14elements.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import swb.framework.Browser;
import swb.framework.BrowserRunner;
import swb.framework.SearchScope;

import javax.inject.Inject;

import static swb.locators.Name.*;
import static swb.locators.TagName.BUTTON;

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
