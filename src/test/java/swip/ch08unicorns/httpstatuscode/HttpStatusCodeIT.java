package swip.ch08unicorns.httpstatuscode;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

public class HttpStatusCodeIT<W extends WebDriver & HasHttpStatusCode> { // You can use generics to require a browser with two aspects.
    @Inject
    private W driver = HttpStatusCodeWebDriverFactory.create(FirefoxDriver::new); // We are using a method reference here. Nothing special, just a Java 8 feature.

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void notFound() throws Exception {
        driver.get("http://127.0.0.1:8080/not-found.html"); // You cannot use "localhost" as we get host not defined error. "127.0.0.1" works successfully.

        assertEquals(404, driver.getHttpStatusCode());
    }
}
