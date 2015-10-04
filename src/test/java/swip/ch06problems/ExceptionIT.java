package swip.ch06problems;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import swip.ch07managingwebdriver.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.fail;

@RunWith(WebDriverRunner.class)
public class ExceptionIT {
    @Inject
    private WebDriver driver;

    @Test
    public void testExceptionMessage() throws Exception {

        try {
            driver.findElement(By.className("does-not-exist"));
            fail();
        } catch (NoSuchElementException e) {
            String format = "| %20s | %20s | %n";
            System.out.println("|===");
            System.out.printf(format, "name", "Description");
            System.out.printf(format, "Additional Information", e.getAdditionalInformation());
            System.out.printf(format, "Build Information", e.getBuildInformation());
            System.out.printf(format, "System Information", e.getSystemInformation());
            System.out.printf(format, "Support URL", e.getSupportUrl());
            System.out.println("|===");
        }
    }
}
