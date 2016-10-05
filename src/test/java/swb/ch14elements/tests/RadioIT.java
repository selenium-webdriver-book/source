package swb.ch14elements.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swb.framework.Browser;
import swb.framework.BrowserRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static swb.locators.Name.CONTACT;
import static swb.locators.Name.FREQUENCY;

@RunWith(BrowserRunner.class)
public class RadioIT {

    @Inject
    private Browser browser;

    @Before
    public void setup() {
        browser.get("/registration-form-new.html");
    }

    @Test
    public void conact() throws Exception {
        assertEquals("email", browser.getRadio(CONTACT));
    }

    @Test
    public void frequency() throws Exception {
        assertEquals("hourly", browser.getRadio(FREQUENCY));
    }

}