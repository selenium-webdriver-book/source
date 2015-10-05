package swip.ch11decorating.httpstatuscode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "web-driver.xml")
public class SpringHttpStatusCodeIT {
    @Inject
    private WebDriver driver;

    @Test
    public void notFound() throws Exception {
        driver.get("http://127.0.0.1:8080/not-found.html");

        assertEquals(404, ((HasHttpStatusCode) driver).getHttpStatusCode());
    }
}
