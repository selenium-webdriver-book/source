package swb.ch07managingwebdriver.screenshot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import swb.framework.WebDriverConfig;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebDriverConfig.class)
@TestExecutionListeners(listeners = {ScreenshotTaker.class, DependencyInjectionTestExecutionListener.class})
public class ScreenshotIT {

    @Inject
    private WebDriver driver;

    @Test
    public void homePageShouldHaveCorrectTitle() throws Exception {

        driver.get("/");

        WebElement heading = driver.findElement(By.cssSelector("h1"));

        assertEquals("Selenium WebDriver Book - Index", heading.getText());
    }
}
