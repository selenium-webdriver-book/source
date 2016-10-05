package swb.ch12wrapping.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swb.framework.HttpStatusCodeSupplier;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class HttpStatusCodeIT {

    @Inject private WebDriver driver;
    @Inject private HttpStatusCodeSupplier httpStatusCodeSupplier;

    @Test
    public void notFound() throws Exception {
        driver.get("/not-found.html");

        assertEquals(404, httpStatusCodeSupplier.get());
    }

    @Test
    public void resourceNotFound() throws Exception {
        driver.get("/resource-not-found.html");

        assertEquals(200, httpStatusCodeSupplier.get());
    }
}
