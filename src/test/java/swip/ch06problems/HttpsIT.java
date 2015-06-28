package swip.ch06problems;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch07managingwebdriver.SeleniumWebDriverRunner;

import javax.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringEndsWith.endsWith;

@RunWith(SeleniumWebDriverRunner.class)
// @Config(exclude = "browserName=htmlunit")
public class HttpsIT {
    @Inject
    private WebDriver driver;

    @Test
    public void smokeHttps() throws Exception {
        driver.get("https://localhost:8443/index.html");

        assertThat(driver.findElement(By.tagName("h1")).getText(), endsWith("Index"));

    }
}
