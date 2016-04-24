package swip.ch14elements.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import swip.ch14elements.v5.Browser;
import swip.ch14elements.v5.BrowserRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        browser.setInputText(By.name("email"), "john.doe@email.com");
        assertEquals("john.doe@email.com", browser.getInputText(By.name("email")));
    }

    @Test
    public void textInputLambda() throws Exception {
        browser.setInputTextLambda(By.name("email"), "john.lambda@email.com");
        assertEquals("john.lambda@email.com", browser.getInputText(By.name("email")));
    }

    @Test
    public void checkbox() throws Exception {
        browser.setCheckboxValue(By.name("terms"), true);
        assertTrue(browser.isChecked(By.name("terms")));
    }

    @Test
    public void radio() throws Exception {
        browser.setRadio(By.cssSelector("input[name='contact']"), "email");
        assertEquals("email", browser.getRadio(By.cssSelector("input[name='contact']")));
    }

    @Test
    public void select() throws Exception {
        browser.getSelect(By.name("interest")).selectByVisibleText("Music");
    }

    @Test
    public void selectLambda() throws Exception {
        browser.getSelectLambda(By.name("interest")).selectByVisibleText("Music");
    }


}