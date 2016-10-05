package swb.ch07managingwebdriver.basic;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@Ignore("will not run on CI")
public class CommonWebDriverIT {

    private final WebDriver driver = new FirefoxDriver();

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Override
    protected void finalize() throws Throwable {
        driver.quit();
        super.finalize();
    }

    @Test
    public void checkTheRegistrationPage() throws Exception {
        // get pages, interact and then verify
    }
}
