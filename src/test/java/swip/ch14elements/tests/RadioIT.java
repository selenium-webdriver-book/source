package swip.ch14elements.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.framework.Browser;
import swip.framework.BrowserRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static swip.locators.Name.CONTACT;
import static swip.locators.Name.FREQUENCY;

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