package swip.ch14elements.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import swip.ch14elements.v5.Browser;
import swip.ch14elements.v5.BrowserRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class RadioIT {

    @Inject
    private Browser browser;

    @Before
    public void setup() {
        browser.get("/registration-form.html");
    }

    @Test
    public void conact() throws Exception {
        assertEquals("email",  browser.getRadio(By.name("contact")));
    }

    @Test
    public void frequency() throws Exception {
        assertEquals("hourly", browser.getRadio(By.name("frequency")));
    }

}