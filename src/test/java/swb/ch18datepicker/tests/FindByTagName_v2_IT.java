package swb.ch18datepicker.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swb.framework.Browser;
import swb.framework.BrowserRunner;
import swb.tests.TestTimer;

import javax.inject.Inject;

import static swb.locators.TagName.INPUT;
import static swb.locators.react.ReactByClassName.TRIGGER_CONTAINER;

@RunWith(BrowserRunner.class)
public class FindByTagName_v2_IT extends TestTimer {

    @Inject
    private Browser browser;

    @Before
    public void setup() {
        browser.get("/react-datepicker.html");
    }

    @Test
    public void trigger() {
        browser.await(TRIGGER_CONTAINER).click(INPUT);
    }
}
