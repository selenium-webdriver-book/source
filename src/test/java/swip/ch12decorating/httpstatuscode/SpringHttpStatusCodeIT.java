package swip.ch12decorating.httpstatuscode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.net.URI;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HttpStatusCodeConfig.class)
public class SpringHttpStatusCodeIT {
    @Inject
    private WebDriver driver;
    @Inject
    private URI baseUrl;

    @Test
    public void notFound() throws Exception {
        driver.get(baseUrl + "/not-found.html");

        assertEquals(404, ((HasHttpStatusCode) driver).getHttpStatusCode());
    }

}
