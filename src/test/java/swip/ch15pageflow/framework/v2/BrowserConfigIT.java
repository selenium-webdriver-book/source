package swip.ch15pageflow.framework.v2;

import org.junit.Test;
import org.junit.runner.RunWith;

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