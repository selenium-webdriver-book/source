package swip.ch13framework.tests;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import swip.ch13framework.v2.Browser;
import swip.ch13framework.v2.BrowserRunner;
import swip.ch13framework.v2.Element;

import javax.inject.Inject;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(BrowserRunner.class)
public class WyswygInputFailureIT {

    private static final Log LOG = LogFactory.getLog(WyswygInputFailureIT.class);
    @Inject
    private Browser driver;
    private Element editorFrame;

    @Before
    public void findElement() {
        driver.get("/wyswyg-editor.html");
        editorFrame = driver.findElement(By.id("editor_ifr"));
    }

    @Test
    public void failToSwitchToIframe() {
        try {
            driver.switchTo().frame(editorFrame);
            fail("It should pass with an exception.");
        } catch (IllegalArgumentException e) {
            LOG.info("======================================");
            LOG.info("The following exception is expected...");
            LOG.info("======================================", e);
            assertTrue(true);
        }
    }
}
