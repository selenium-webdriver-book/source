package swip.ch15pageflow.framework.v1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class NaiveRadioIT {

    @Inject
    private Browser browser;
    private String value;

    @Before
    public void setup() {
        browser.get("/registration-form.html");
    }

    @Test
    public void conact() throws Exception {
        List<WebElement> radiobuttons =  browser
            .findElements(By.name("contact"));

        assert radiobuttons.size() >=2;

        for (WebElement e : radiobuttons) {
            if (Boolean.valueOf(e.getAttribute("checked"))) {
                value = e.getAttribute("value");
                break;
            }
        }
        assertEquals("email", value);
    }


    @Test
    public void frequency() throws Exception {
        List<WebElement> radiobuttons = browser
            .findElements(By.name("frequency"));

        assert radiobuttons.size() >= 2;

        for (WebElement e : radiobuttons) {
            if (Boolean.valueOf(e.getAttribute("checked"))) {
                value = e.getAttribute("value");
                break;
            }
        }
        assertEquals("hourly", value);
    }

}