package swb.ch08windows.modals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;
import java.net.URI;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class HttpAuthenticationIT {
    @Inject
    private WebDriver driver;
    @Inject
    private URI baseUri;

    @Test
    public void authViaUrl() throws Exception {
        driver.get("http://admin:secret@" + baseUri.getHost() + ":" + baseUri.getPort() + "/auth.html");

        assertEquals("You Are Logged In", driver.findElement(By.tagName("h1")).getText());
    }
}
