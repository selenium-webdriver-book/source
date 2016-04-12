package swip.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import swip.framework.Browser;
import swip.framework.BrowserRunner;

import javax.inject.Inject;

@RunWith(BrowserRunner.class)
public class BrowserConfigIT {

    @Inject
    private Browser browser;

    @Test
    public void pass() throws Exception {
        browser.get("/");
    }
}