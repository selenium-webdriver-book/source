package swip.ch15pageflow.v1.framework;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class RadioIT {

    @Inject
    private Browser browser;
    private String value;

    @Before
    public void setup() {
        browser.get("/registration-form.html");
    }

    @Test
    public void conact() throws Exception {
        value = browser.getRadio(() -> By.name("contact"));
        assertEquals("email",  value);
    }


    @Test
    public void frequency() throws Exception {
        value = browser.getRadio(() -> By.name("frequency"));
        assertEquals("hourly", value);
    }

}