package swb.ch07managingwebdriver.basic;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@Ignore("will not run on CI")
public class FinalizeWebDriverIT {

    private final WebDriver driver = new FirefoxDriver();

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
