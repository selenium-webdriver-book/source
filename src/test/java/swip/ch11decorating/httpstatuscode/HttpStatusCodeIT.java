package swip.ch11decorating.httpstatuscode;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

public class HttpStatusCodeIT {
    @Inject
    private final WebDriver driver = HttpStatusCodeWebDriverFactory.create(FirefoxDriver::new);

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void notFound() throws Exception {
        driver.get("http://127.0.0.1:8080/not-found.html");

        assertEquals(404, ((HasHttpStatusCode) driver).getHttpStatusCode());
    }
}
