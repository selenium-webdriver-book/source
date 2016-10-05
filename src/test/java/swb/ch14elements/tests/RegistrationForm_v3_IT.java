package swb.ch14elements.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swb.framework.Browser;
import swb.framework.BrowserRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static swb.locators.CssSelector.CONTACT;
import static swb.locators.Name.*;

@RunWith(BrowserRunner.class)
public class RegistrationForm_v3_IT {

    @Inject
    private Browser browser;

    @Before
    public void setUp() throws Exception {
        browser.get("/registration-form.html");
    }

    @Test
    public void textInput() throws Exception {
        browser.setInputText(EMAIL, "john.doe@email.com");
        assertEquals("john.doe@email.com", browser.getInputText(EMAIL));
    }

    @Test
    public void checkbox() throws Exception {
        browser.setCheckboxValue(TERMS, true);
        assertTrue(browser.isChecked(TERMS));
    }

    @Test
    public void radio() throws Exception {
        browser.setRadio(CONTACT, "email");
        assertEquals("email", browser.getRadio(CONTACT));
    }

    @Test
    public void select() throws Exception {
        browser.getSelect(INTEREST).selectByVisibleText("Music");
    }

}