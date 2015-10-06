package swip.ch07managingwebdriver.injecting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebDriverConfig.class)
public class InjectedIT {
    @Inject
    private WebDriver driver;

    @Test
    public void loadIndexPage() throws Exception {
        driver.get("/index.html");
    }

    @Test
    @DirtiesContext
    public void dirtyTheDriver() throws Exception {
        driver.get("http://localhost:8080/popups.html");

        driver.findElement(By.linkText("Prompt")).click();
    }
}
