package swb.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import swb.framework.Browser;
import swb.framework.BrowserRunner;

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